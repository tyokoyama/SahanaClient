package jp.sahana.chugokugtug;

import jp.sahana.chugokugtug.async.GetRequestListTask;
import jp.sahana.chugokugtug.data.Request;
import jp.sahana.chugokugtug.util.AppSetting;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Request req = (Request)l.getItemAtPosition(position);
		String url = req.getUrl();
		String[] array = url.split("/");
		int req_id = Integer.parseInt(array[array.length - 1]);
		Intent intent = new Intent(this, RequestItemInputActivity.class);
		intent.putExtra("req_id", req_id);
		startActivity(intent);
	}
}
