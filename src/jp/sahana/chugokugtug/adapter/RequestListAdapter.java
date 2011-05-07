package jp.sahana.chugokugtug.adapter;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.data.Request;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RequestListAdapter extends ArrayAdapter<Request> {
	private LayoutInflater mInflater;

	public RequestListAdapter(Context context) {
		super(context, 0);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.request_layout, null);
			
			holder.txtDate = (TextView)convertView.findViewById(R.id.txtdate);
			holder.txtType = (TextView)convertView.findViewById(R.id.txttype);
			holder.txtCommitStatus = (TextView)convertView.findViewById(R.id.txtcommit_status);
			holder.txtTransitStatus = (TextView)convertView.findViewById(R.id.txttransit_status);
			holder.txtFulFilStatus = (TextView)convertView.findViewById(R.id.txtfulfil_status);
			holder.txtCancel = (TextView)convertView.findViewById(R.id.txtcancel);
			holder.txtModifiedBy = (TextView)convertView.findViewById(R.id.txtmodifiedby);
			holder.txtModifyDate = (TextView)convertView.findViewById(R.id.txtmodifieddate);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		Request request = this.getItem(position);
		if(request != null) {
			holder.txtDate.setText(request.getDate());
			holder.txtType.setText(request.getTypeName());
			holder.txtCommitStatus.setText(request.getCommitStatusName());
			holder.txtTransitStatus.setText(request.getTransitStatusName());
			holder.txtFulFilStatus.setText(request.getFulfilStatusName());
			if(!request.isCancel()) {
				holder.txtCancel.setVisibility(View.INVISIBLE);
			}
			holder.txtModifiedBy.setText(request.getModifiedBy());
			holder.txtModifyDate.setText(request.getModifyDate());
		}
		
		return convertView;
	}
	
	class ViewHolder {
		TextView txtDate;
		TextView txtType;
		TextView txtCommitStatus;
		TextView txtTransitStatus;
		TextView txtFulFilStatus;
		TextView txtCancel;
		TextView txtModifiedBy;
		TextView txtModifyDate;
	}
}
