package jp.sahana.chugokugtug.data;

import java.util.ArrayList;

public class ProjectList extends BaseResponse {
	
	public ProjectList() {
		array = new ArrayList<Project>();
	}
	
	private ArrayList<Project> array;

	public ArrayList<Project> getArray() {
		return array;
	}

	public void setArray(ArrayList<Project> array) {
		this.array = array;
	}

}
