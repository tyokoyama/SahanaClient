package jp.sahana.chugokugtug.async;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.adapter.ShelterAdapter;
import jp.sahana.chugokugtug.data.Shelter;
import jp.sahana.chugokugtug.data.ShelterList;
import jp.sahana.chugokugtug.data.ShelterListFactory;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

public class GetShelterTask extends AsyncTask<String, Void, Boolean> {
	private ProgressDialog mDialog = null;
	private ListView mList = null;
	private ShelterAdapter mAdapter = null;

	public GetShelterTask(Context context, ListView listView) {
		mAdapter = new ShelterAdapter(context);
		mDialog = new ProgressDialog(context);
		mDialog.setMessage(context.getString(R.string.msg_shelter_list_wait));
		mDialog.setIndeterminate(true);
		
		mList = listView;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog.show();
	}

	@Override
	protected Boolean doInBackground(String... param) {
		boolean bRet = false;
		String strUrl = param[0];
		
		SahanaHttpClient client = new SahanaHttpClient();
		int iRet = client.getData(strUrl + "/eden/cr/shelter.xml");
		if(iRet == HttpStatus.SC_OK) {
			ShelterListFactory factory = new ShelterListFactory();
			ShelterList list = (ShelterList)factory.create(client.getResponse());
			
			for(Shelter shelter : list.getArray()) {
				mAdapter.add(shelter);
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
