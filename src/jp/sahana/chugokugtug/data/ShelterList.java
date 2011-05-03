package jp.sahana.chugokugtug.data;

import java.util.ArrayList;

public class ShelterList extends BaseResponse {
	private ArrayList<Shelter> array;

	public ShelterList() {
		array = new ArrayList<Shelter>();
	}
	
	public ArrayList<Shelter> getArray() {
		return array;
	}

	public void setArray(ArrayList<Shelter> array) {
		this.array = array;
	}
	
}
