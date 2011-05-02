package jp.sahana.chugokugtug.data;

import java.util.ArrayList;

public class ProjectTaskList extends BaseResponse {
	private ArrayList<ProjectTask> array;
	
	public ProjectTaskList() {
		array = new ArrayList<ProjectTask>();
	}

	public ArrayList<ProjectTask> getArray() {
		return array;
	}

	public void setArray(ArrayList<ProjectTask> array) {
		this.array = array;
	}
}
