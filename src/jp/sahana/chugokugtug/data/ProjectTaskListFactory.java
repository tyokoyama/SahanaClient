package jp.sahana.chugokugtug.data;

import org.xmlpull.v1.XmlPullParser;

public class ProjectTaskListFactory extends ResourceFactory {
	private ProjectTaskList mTaskList;
	private ProjectTask mTask;
	private String field;
	
	@Override
	protected void initialize() {
		mTaskList = new ProjectTaskList();
	}

	@Override
	protected BaseResponse getResponse() {
		return mTaskList;
	}

	@Override
	protected void onStartResource(XmlPullParser parser) {
		String name = this.getName();
		if(name.equals("project_task")) {
			mTask = new ProjectTask();
			mTask.setCreateDate(getAttribute(parser, "created_on"));
			mTask.setModifyDate(getAttribute(parser,"modified_on"));
			mTask.setCreatedBy(getAttribute(parser, "created_by"));
			mTask.setModifiedBy(getAttribute(parser, "modified_by"));
			mTask.setUrl(getAttribute(parser, "url"));
		} else {
			if(mTask != null) {
				mTaskList.getArray().add(mTask);
				mTask = null;
			}
		}
	}

	@Override
	protected void onEndResource(XmlPullParser parser) {
		String name = this.getName();
		if(mTask != null) {
			if(name.equals("project_task")) {
				mTaskList.getArray().add(mTask);
				mTask = null;
			}
		}
	}

	@Override
	protected void onStartData(XmlPullParser parser) {
		if(mTask != null) {
			field = getAttribute(parser, "field");
			String value = getAttribute(parser, "value");
			if(field.equals("urgent")) {
				mTask.setUrgent(value.equals("true"));
			} else if(field.equals("priority")) {
				mTask.setPriority(convertInt(value));
			} else if(field.equals("status")) {
				mTask.setStatus(convertInt(value));
			}
		}
	}

	@Override
	protected void onEndData(XmlPullParser parser) {
		if(mTask != null) {
			if(field.equals("priority")) {
				mTask.setPriorityName(mText);
			} else if(field.equals("status")) {
				mTask.setStatusName(mText);
			} else if(field.equals("subject")) {
				mTask.setSubject(mText);
			} else if(field.equals("description")) {
				mTask.setDescription(mText);
			}
		}
	}

}
