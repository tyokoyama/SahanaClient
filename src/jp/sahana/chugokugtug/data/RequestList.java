package jp.sahana.chugokugtug.data;

import java.util.ArrayList;

public class RequestList extends BaseResponse {
	private ArrayList<Request> array;

	public RequestList() {
		array = new ArrayList<Request>();
	}
	
	public ArrayList<Request> getArray() {
		return array;
	}

	public void setArray(ArrayList<Request> array) {
		this.array = array;
	}
	
}
