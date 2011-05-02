package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.async.GetProjectListTask;
import jp.sahana.chugokugtug.data.Project;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class VolunteerActivity extends ListActivity {
	private GetProjectListTask mProjectTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volunteer);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		ListView list = (ListView)findViewById(android.R.id.list);
		mProjectTask = new GetProjectListTask(this, list);
		mProjectTask.execute();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		if(mProjectTask != null && !mProjectTask.isCancelled()) {
			mProjectTask.cancel(true);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Project prj = (Project)l.getItemAtPosition(position);
		if(prj != null) {
			
		}
	}
	
}
