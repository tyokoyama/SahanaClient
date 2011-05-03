package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.util.AppSetting;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final int SETTING_REQUEST = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        buttonControl();
    }
    
    public void volunteerClick(View view) {
    	Intent intent = new Intent(this, VolunteerActivity.class);
    	startActivity(intent);
    }
    
    public void shelterClick(View view) {
    	
    }
    
    public void settingClick(View view) {
    	Intent intent = new Intent(this, SettingActivity.class);
    	startActivityForResult(intent, SETTING_REQUEST);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == SETTING_REQUEST) {
			buttonControl();
		}
	}
	
	private void buttonControl() {
		int[] iButtons = new int[] {
			 R.id.btnvolunteer
			,R.id.btnshelter
		};
        AppSetting setting = new AppSetting(this);
        if(setting.getSiteURL() == null) {
        	// 接続先URLがない場合は設定以外処理させない。
        	for(int id : iButtons) {
        		Button btn = (Button)findViewById(id);
        		btn.setEnabled(false);
        	}
        } else {
        	for(int id : iButtons) {
        		Button btn = (Button)findViewById(id);
        		btn.setEnabled(true);
        	}
        }

	}
}