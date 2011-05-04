package jp.sahana.chugokugtug.adapter;

import jp.sahana.chugokugtug.R;
import jp.sahana.chugokugtug.data.Shelter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShelterAdapter extends ArrayAdapter<Shelter> {
	private LayoutInflater mInflater;
	private String CAPACITY_FORMAT;
	
	public ShelterAdapter(Context context) {
		super(context, 0);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		CAPACITY_FORMAT = context.getString(R.string.txt_capacity_format);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.shelter_layout, null);
			holder = new ViewHolder();
			holder.txtName = (TextView)convertView.findViewById(R.id.txtname);
			holder.txtAddress = (TextView)convertView.findViewById(R.id.txtaddress);
			holder.txtType = (TextView)convertView.findViewById(R.id.txttype);
			holder.txtService = (TextView)convertView.findViewById(R.id.txtservice);
			holder.txtModifiedBy = (TextView)convertView.findViewById(R.id.txtmodifiedby);
			holder.txtModifiedDate = (TextView)convertView.findViewById(R.id.txtmodifieddate);
			holder.txtCapacity = (TextView)convertView.findViewById(R.id.txtcapacity);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		Shelter shelter = this.getItem(position);
		if(shelter != null) {
			holder.txtName.setText(shelter.getName());
			holder.txtAddress.setText(shelter.getAddress());
			holder.txtType.setText(shelter.getShelter_type_id());
			holder.txtService.setText(shelter.getShelter_service_id());
			holder.txtModifiedBy.setText(shelter.getModifiedBy());
			holder.txtModifiedDate.setText(shelter.getModifyDate());
			holder.txtCapacity.setText(String.format(CAPACITY_FORMAT, shelter.getCapacity()));
		}
		
		return convertView;
	}
	
	class ViewHolder {
		public TextView txtName;
		public TextView txtAddress;
		public TextView txtType;
		public TextView txtService;
		public TextView txtModifiedBy;
		public TextView txtModifiedDate;
		public TextView txtCapacity;
	}
}
