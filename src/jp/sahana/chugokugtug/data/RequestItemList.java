package jp.sahana.chugokugtug.data;

import java.util.ArrayList;

public class RequestItemList extends BaseResponse {
	private ArrayList<RequestItem> array;

	public RequestItemList() {
		array = new ArrayList<RequestItem>();
	}

	public ArrayList<RequestItem> getArray() {
		return array;
	}

	public void setArray(ArrayList<RequestItem> array) {
		this.array = array;
	}
}
