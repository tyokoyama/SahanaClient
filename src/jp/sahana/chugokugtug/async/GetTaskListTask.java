package jp.sahana.chugokugtug.async;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.adapter.ProjectTaskListAdapter;
import jp.sahana.chugokugtug.data.ProjectTask;
import jp.sahana.chugokugtug.data.ProjectTaskList;
import jp.sahana.chugokugtug.data.ProjectTaskListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class GetTaskListTask extends AsyncTask<String, Void, Boolean> {
	private ProgressDialog mDialog = null;
	private ListView mList = null;
	private ProjectTaskListAdapter mAdapter = null;

	public GetTaskListTask(Context context, ListView listView) {
		mAdapter = new ProjectTaskListAdapter(context);
		mDialog = new ProgressDialog(context);
		mDialog.setMessage(context.getString(R.string.msg_task_list_wait));
		mDialog.setIndeterminate(true);
		
		mList = listView;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog.show();
	}

	@Override
	protected Boolean doInBackground(String... arg0) {
		String url = arg0[0];
		
		boolean bRet = false;
		SahanaHttpClient client = new SahanaHttpClient();
		int iRet = client.get(url);
		if(iRet == HttpStatus.SC_OK) { 
			ProjectTaskListFactory factory = new ProjectTaskListFactory();
			ProjectTaskList list = (ProjectTaskList)factory.create(client.getResponse());
			
			for(ProjectTask task : list.getArray()) {
				mAdapter.add(task);
			}
			
			bRet = true;
		}
		
		return bRet;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if(result) {
			mList.setAdapter(mAdapter);
		}
		mDialog.dismiss();
	}

}
