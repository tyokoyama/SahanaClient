package jp.sahana.chugokugtug.adapter;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.data.ProjectTask;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProjectTaskListAdapter extends ArrayAdapter<ProjectTask> {
	private LayoutInflater mInflater;
	
	public ProjectTaskListAdapter(Context context) {
		super(context, 0);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.volunteer_task_layout, null);
			
			holder = new ViewHolder();
			holder.txtSubject = (TextView)convertView.findViewById(R.id.txtsubject);
			holder.txtDescription = (TextView)convertView.findViewById(R.id.txtdescription);
			holder.txtStatus = (TextView)convertView.findViewById(R.id.txtstatus);
			holder.txtPriority = (TextView)convertView.findViewById(R.id.txtpriority);
			holder.txtUrgent = (TextView)convertView.findViewById(R.id.txturgent);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		ProjectTask task = this.getItem(position);
		if(task != null) {
			holder.txtSubject.setText(task.getSubject());
			holder.txtDescription.setText(task.getDescription());
			holder.txtPriority.setText(task.getPriorityName());
			holder.txtStatus.setText(task.getStatusName());
			if(task.isUrgent()) {
				holder.txtUrgent.setVisibility(View.VISIBLE);
			} else {
				holder.txtUrgent.setVisibility(View.INVISIBLE);
			}
		}
		
		return convertView;
	}
	
	class ViewHolder {
		public TextView txtSubject;
		public TextView txtDescription;
		public TextView txtStatus;
		public TextView txtPriority;
		public TextView txtUrgent;
	}
}
