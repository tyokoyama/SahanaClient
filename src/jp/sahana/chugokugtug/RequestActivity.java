package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.async.GetRequestListTask;
import jp.sahana.chugokugtug.util.AppSetting;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class RequestActivity extends ListActivity {
	private GetRequestListTask mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volunteer);
		
		TextView txtTitle = (TextView)findViewById(R.id.txttitle);
		txtTitle.setText(R.string.txt_request_title);
		
		TextView txtEmpty = (TextView)findViewById(android.R.id.empty);
		txtEmpty.setText(R.string.txt_request_empty);

		AppSetting setting = new AppSetting(this);
		
		ListView list = (ListView)findViewById(android.R.id.list);
		mTask = new GetRequestListTask(this, list);
		mTask.execute(setting.getSiteURL());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mTask != null && !mTask.isCancelled()) {
			mTask.cancel(true);
		}
	}
}
