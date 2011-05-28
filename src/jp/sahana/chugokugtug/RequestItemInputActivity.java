package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.async.PutRequestItemTask;
import jp.sahana.chugokugtug.put.PutRequestItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RequestItemInputActivity extends Activity {
	private PutRequestItem mItem;
	private PutRequestItemTask mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_item_input);
		
		mItem = new PutRequestItem();

		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			if(extras.containsKey("req_id")) {
				mItem.setReqId(extras.getInt("req_id"));
			} else {
				finish();
				return;
			}
		}
	}

	public void btnBackClick(View v) {
		finish();
	}
	
	public void btnInputClick(View v) {
		if(!checkEditText(R.id.edititem, R.string.error_no_item)) return;
		if(!checkEditText(R.id.editquantity, R.string.error_no_quantity)) return;
		EditText editQuantity;
		editQuantity = (EditText)findViewById(R.id.editquantity);
		int quantity = Integer.parseInt(editQuantity.getText().toString());
		if(quantity <= 0) {
			editQuantity.setError(getString(R.string.error_miss_quantity));
			return;
		}
		EditText edit;
		edit = (EditText)findViewById(R.id.edititem);
		int reqItemId = Integer.parseInt(edit.getText().toString());

		edit = (EditText)findViewById(R.id.editcomment);
		String comments = edit.getText().toString();
		
		mItem.setReqItemId(reqItemId);
		mItem.setQuantity(quantity);
		mItem.setComments(comments);
		
		// 更新
		mTask = new PutRequestItemTask(this, mItem, editQuantity);
		mTask.execute();
	}
	
	private boolean checkEditText(int id, int errmsgId) {
		boolean bRet = false;
		EditText edit = (EditText)findViewById(id);
		String strUrl = edit.getText().toString();
		if(strUrl.trim().length() <= 0) {
			edit.setError(getString(errmsgId));
		} else {
			bRet = true;
		}
		
		return bRet;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mTask != null && !mTask.isCancelled()) {
			mTask.cancel(true);
		}
	}

}
