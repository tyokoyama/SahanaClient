package jp.sahana.chugokugtug.async;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.adapter.ProjectListAdapter;
import jp.sahana.chugokugtug.data.Project;
import jp.sahana.chugokugtug.data.ProjectList;
import jp.sahana.chugokugtug.data.ProjectListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class GetProjectListTask extends AsyncTask<String, Void, Boolean> {
	private ProgressDialog mDialog = null;
	private ListView mList = null;
	private ProjectListAdapter mPrjAdapter;
	
	public GetProjectListTask(Context context, ListView listView) {
		mPrjAdapter = new ProjectListAdapter(context);
		
		mDialog = new ProgressDialog(context);
		mDialog.setMessage(context.getString(R.string.msg_project_list_wait));
		mDialog.setIndeterminate(true);
		mList = listView;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog.show();
	}

	@Override
	protected Boolean doInBackground(String... params) {
		String strUrl = params[0];
		boolean bRet = false;
		SahanaHttpClient client = new SahanaHttpClient();
		
		int iRet = client.getData(strUrl + "/eden/vol/project.xml");
		if(iRet == HttpStatus.SC_OK) { 
			ProjectListFactory factory = new ProjectListFactory();
			ProjectList prjList = (ProjectList)factory.create(client.getResponse());

			for(Project prj : prjList.getArray()) {
				mPrjAdapter.add(prj);
			}

			bRet = true;
		}
		
		return bRet;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		if(result) {
			mList.setAdapter(mPrjAdapter);
		}
		
		mDialog.dismiss();
	}

}
