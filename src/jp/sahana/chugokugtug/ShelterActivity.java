package jp.sahana.chugokugtug;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShelterActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volunteer);
		
		TextView txtTitle = (TextView)findViewById(R.id.txttitle);
		txtTitle.setText(R.string.txt_shelter_title);
		
		TextView txtEmpty = (TextView)findViewById(android.R.id.empty);
		txtEmpty.setText(R.string.shelter_list_empty);

	}

}
