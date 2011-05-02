package jp.sahana.chugokugtug.data;

import org.xmlpull.v1.XmlPullParser;

public abstract class ResourceFactory extends BaseResponseFactory {
	private String name;
	
	@Override
	protected void onStartTag(XmlPullParser parser) {
		String strTagName = parser.getName();
		if(strTagName.equals("resource")) {
			name = getAttribute(parser, "name");
			onStartResource(parser);
		} else if(strTagName.equals("data")) {
			onStartData(parser);
		}
	}

	@Override
	protected void onEndTag(XmlPullParser parser) {
		String strTagName = parser.getName();
		if(strTagName.equals("resource")) {
			onEndResource(parser);			
		} else if(strTagName.equals("data")) {
			onEndData(parser);
		}
	}

	protected String getName() {
		return this.name;
	}
	
	protected abstract void onStartResource(XmlPullParser parser);
	protected abstract void onEndResource(XmlPullParser parser);
	protected abstract void onStartData(XmlPullParser parser);
	protected abstract void onEndData(XmlPullParser parser);
}
