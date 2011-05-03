package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.util.AppSetting;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingActivity extends Activity {
	private AppSetting setting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		setting = new AppSetting(this);
		
		EditText edit = (EditText)findViewById(R.id.editurl);
		edit.setText(setting.getSiteURL());
		edit = (EditText)findViewById(R.id.editusername);
		edit.setText(setting.getUserName());
		edit = (EditText)findViewById(R.id.editpassword);
		edit.setText(setting.getPassword());
	}

	public void commitClick(View v) {
		// 入力チェック
		boolean bCheckURL = checkEditText(R.id.editurl, R.string.error_no_site_url);
		boolean bCheckUserName = checkEditText(R.id.editusername, R.string.error_no_username);
		boolean bCheckPassword = checkEditText(R.id.editpassword, R.string.error_no_password);
		
		if(bCheckURL && bCheckUserName && bCheckPassword) {
			EditText edit = (EditText)findViewById(R.id.editurl);
			setting.setSiteURL(edit.getText().toString().trim());
			edit = (EditText)findViewById(R.id.editusername);
			setting.setUserName(edit.getText().toString().trim());
			edit = (EditText)findViewById(R.id.editpassword);
			setting.setPassword(edit.getText().toString().trim());
			setting.commit();
			finish();
		}
	}
	
	public void backClick(View v) {
		finish();
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
}
