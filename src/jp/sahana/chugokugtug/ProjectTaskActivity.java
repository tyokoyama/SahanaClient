package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.async.GetTaskListTask;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ProjectTaskActivity extends ListActivity {
	private GetTaskListTask mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volunteer);
		
		String url = "";
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			if(extras.containsKey("url")) {
				url = extras.getString("url");
			}
		}
		
		TextView txtTitle = (TextView)findViewById(R.id.txttitle);
		txtTitle.setText(R.string.txt_task_title);
		
		TextView txtEmpty = (TextView)findViewById(android.R.id.empty);
		txtEmpty.setText(R.string.task_list_empty);
		
		ListView list = (ListView)findViewById(android.R.id.list);
		mTask = new GetTaskListTask(this, list);
		mTask.execute(url);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mTask != null && !mTask.isCancelled()) {
			mTask.cancel(true);
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}
}
