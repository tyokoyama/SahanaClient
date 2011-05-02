package jp.sahana.chugokugtug.data;

import org.xmlpull.v1.XmlPullParser;

public class ProjectListFactory extends BaseResponseFactory {
	private ProjectList projectList;
	private Project project;
	private String field;
	private String name;
	
	@Override
	protected void onStartTag(XmlPullParser parser) {
		String strTagName = parser.getName();
		if(strTagName.equals("resource")) {
			name = getAttribute(parser, "name");
			if(name.equals("project_project")) {
				project = new Project();
				project.setCreateBy(getAttribute(parser, "created_by"));
				project.setModifiedBy(getAttribute(parser, "modified_by"));
				project.setUrl(getAttribute(parser, "url"));
				project.setCreateDate(getAttribute(parser, "created_on"));
				project.setModifyDate(getAttribute(parser, "modified_on"));
			} else {
				if(project != null) {
					// project_project以外にもresourceタグのデータがあるため、閉じタグが来た時点でproject_projectのデータは完結したとみなす。
					projectList.getArray().add(project);
					project = null;
				}
			}
			
		} else if(strTagName.equals("data")) {
			if(project != null) {
				// dataはテキストに入っているので、終了タグの時に取り込む
				field = getAttribute(parser, "field");
				if(field.equals("status")) {
					// Statusは値は属性の中、文言はテキストにある。
					project.setStatus(convertInt(getAttribute(parser, "value")));
				} else if(field.equals("start_date")) {
					project.setStartDate(getAttribute(parser, "value"));
				} else if(field.equals("end_date")) {
					project.setEndDate(getAttribute(parser, "value"));
				}
			}
		}
	}

	@Override
	protected void onEndTag(XmlPullParser parser) {
		String strTagName = parser.getName();
		if(strTagName.equals("data")) {
			if(field.equals("name")) {
				project.setName(mText);
			} else if(field.equals("status")) {
				project.setStatusName(mText);
			}
		} else if(strTagName.equals("resource")) {
			if(project != null) {
				// project_project以外にもresourceタグのデータがあるため、閉じタグが来た時点でproject_projectのデータは完結したとみなす。
				projectList.getArray().add(project);
				project = null;
			}
		}
		
	}

	@Override
	protected BaseResponse getResponse() {
		return projectList;
	}

	@Override
	protected void initialize() {
		projectList = new ProjectList();
	}

}
