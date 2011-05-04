package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.async.GetShelterTask;
import jp.sahana.chugokugtug.util.AppSetting;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ShelterActivity extends ListActivity {
	private GetShelterTask mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volunteer);
		
		TextView txtTitle = (TextView)findViewById(R.id.txttitle);
		txtTitle.setText(R.string.txt_shelter_title);
		
		TextView txtEmpty = (TextView)findViewById(android.R.id.empty);
		txtEmpty.setText(R.string.shelter_list_empty);

		AppSetting setting = new AppSetting(this);
		
		ListView list = (ListView)findViewById(android.R.id.list);
		mTask = new GetShelterTask(this, list);
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
