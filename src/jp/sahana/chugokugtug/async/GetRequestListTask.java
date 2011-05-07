package jp.sahana.chugokugtug.async;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.adapter.RequestListAdapter;
import jp.sahana.chugokugtug.data.Request;
import jp.sahana.chugokugtug.data.RequestList;
import jp.sahana.chugokugtug.data.RequestListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class GetRequestListTask extends AsyncTask<String, Void, Boolean> {
	private RequestListAdapter mAdapter;
	private ProgressDialog mDialog;
	private ListView mListView;
	
	public GetRequestListTask(Context context, ListView list) {
		mAdapter = new RequestListAdapter(context);
		mDialog = new ProgressDialog(context);
		mDialog.setMessage(context.getString(R.string.msg_request_list_wait));
		mDialog.setIndeterminate(true);
		
		mListView = list;
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
		int iRet = client.get(url + "/eden/req/req.xml");
		if(iRet == HttpStatus.SC_OK) {
			RequestListFactory factory = new RequestListFactory();
			RequestList list = (RequestList)factory.create(client.getResponse());
			
			for(Request req : list.getArray()) {
				mAdapter.add(req);
			}
			
			bRet = true;
		}
		
		return bRet;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		if(result) {
			mListView.setAdapter(mAdapter);
		}
		
		mDialog.dismiss();
	}

}
