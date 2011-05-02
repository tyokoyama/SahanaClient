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

public class GetProjectListTask extends AsyncTask<Void, Void, Boolean> {
	private ProgressDialog mDialog = null;
	private ListView mList = null;
	private ProjectList mPrjList;
	ProjectListAdapter mPrjAdapter;
	
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
	protected Boolean doInBackground(Void... params) {
		boolean bRet = false;
		SahanaHttpClient client = new SahanaHttpClient();
		
		int iRet = client.getData("http://sahana.jp/eden/vol/project.xml");
		if(iRet == HttpStatus.SC_OK) { 
			ProjectListFactory factory = new ProjectListFactory();
			mPrjList = (ProjectList)factory.create(client.getResponse());
			
			bRet = true;
		}
		
		return bRet;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		for(Project prj : mPrjList.getArray()) {
			mPrjAdapter.add(prj);
		}
		
		mList.setAdapter(mPrjAdapter);
		
		mDialog.dismiss();
	}

}
