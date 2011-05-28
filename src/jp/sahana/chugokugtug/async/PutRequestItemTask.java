package jp.sahana.chugokugtug.async;

import org.apache.http.HttpStatus;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.put.PutRequestItem;
import jp.sahana.chugokugtug.put.PutRequestItemFactory;
import jp.sahana.chugokugtug.util.AppSetting;
import jp.sahana.chugokugtug.web.SahanaHttpClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

public class PutRequestItemTask extends AsyncTask<Void, Void, Boolean> {
	private Context mContext;
	private ProgressDialog mDialog;
	private EditText mEdit;
	private PutRequestItem mItem;
	private AppSetting mSetting;
 
	public PutRequestItemTask(Context context, PutRequestItem item, EditText edit) {
		mDialog = new ProgressDialog(context);
		mDialog.setMessage(context.getString(R.string.msg_request_item_update_wait));
		mDialog.setIndeterminate(true);
	
		mContext = context;
		mEdit = edit;
		mItem = item;
		mSetting = new AppSetting(context);
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		mDialog.show();
	}

	@Override
	protected Boolean doInBackground(Void... arg0) {
		boolean bRet = false;
		
		PutRequestItemFactory factory = new PutRequestItemFactory(mItem);
		String strXML = factory.createXml();
		SahanaHttpClient client = new SahanaHttpClient(mSetting.getUserName(), mSetting.getPassword());
		int iRet = client.put(mSetting.getSiteURL() + "/eden/req/req_item/create.xml", strXML);
		if(iRet == HttpStatus.SC_OK) {
			String response = client.getResponse();
			if(response.equals("{\"status\": \"success\", \"statuscode\": \"200\"}")) {
				// 結果のレスポンスが正しいものが来たときのみOKとする。
				bRet = true;
			}
		}
		
		return bRet;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		int id;
		
		super.onPostExecute(result);
		if(result) {
			id = R.string.msg_update_success;
			mEdit.setText("");				// 成功時は入力をクリアする（重複登録を防ぐため）
		} else {
			id = R.string.msg_update_failure;
		}
		
		mDialog.dismiss();
		
		Toast.makeText(mContext, mContext.getString(id), Toast.LENGTH_SHORT).show();
	}

}
