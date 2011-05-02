package jp.sahana.chugokugtug.adapter;

import jp.sahana.chugokugtug.data.ProjectTask;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

public class ProjectTaskListAdapter extends ArrayAdapter<ProjectTask> {
	private LayoutInflater mInflater;
	
	public ProjectTaskListAdapter(Context context) {
		super(context, 0);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
}
