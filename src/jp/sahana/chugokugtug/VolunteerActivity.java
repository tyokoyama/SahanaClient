package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.async.GetProjectListTask;
import jp.sahana.chugokugtug.data.Project;
import jp.sahana.chugokugtug.util.AppSetting;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class VolunteerActivity extends ListActivity {
	private GetProjectListTask mProjectTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volunteer);
		
		AppSetting setting = new AppSetting(this);
		
		ListView list = (ListView)findViewById(android.R.id.list);
		mProjectTask = new GetProjectListTask(this, list);
		mProjectTask.execute(setting.getSiteURL());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mProjectTask != null && !mProjectTask.isCancelled()) {
			mProjectTask.cancel(true);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Project prj = (Project)l.getItemAtPosition(position);
		if(prj != null) {
			Intent intent = new Intent(this, ProjectTaskActivity.class);
			intent.putExtra("url", prj.getUrl() + "/task.xml");
			startActivity(intent);
		}
	}
	
}
