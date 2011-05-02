package jp.sahana.chugokugtug.adapter;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.data.Project;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProjectListAdapter extends ArrayAdapter<Project> {
	private LayoutInflater mInflater;

	public ProjectListAdapter(Context context) {
		super(context, 0);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			holder = new ViewHolder();
			
			convertView = mInflater.inflate(R.layout.volunteer_project_layout, null);
			
			holder.txtTitle = (TextView)convertView.findViewById(R.id.txttitle);
			holder.txtStartDate = (TextView)convertView.findViewById(R.id.txtstartdate);
			holder.txtEndDate = (TextView)convertView.findViewById(R.id.txtenddate);
			holder.txtBetween = (TextView)convertView.findViewById(R.id.textView1);
			holder.txtStatus = (TextView)convertView.findViewById(R.id.txtstatus);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		Project prj = this.getItem(position);
		if(prj != null) {
			holder.txtTitle.setText(prj.getName());
			holder.txtStatus.setText(prj.getStatusName());
			if(prj.getStartDate() != null && prj.getStartDate().length() > 0) {
				holder.txtStartDate.setText(prj.getStartDate());
				holder.txtEndDate.setText(prj.getEndDate());
				
				holder.txtStartDate.setVisibility(View.VISIBLE);
				holder.txtBetween.setVisibility(View.VISIBLE);
				holder.txtEndDate.setVisibility(View.VISIBLE);
			} else {
				holder.txtStartDate.setVisibility(View.INVISIBLE);
				holder.txtBetween.setVisibility(View.INVISIBLE);
				holder.txtEndDate.setVisibility(View.INVISIBLE);
			}
		}
		
		return convertView;
	}

	class ViewHolder {
		public TextView txtTitle;
		public TextView txtStartDate;
		public TextView txtEndDate;
		public TextView txtBetween;
		public TextView txtStatus;
	}
}
