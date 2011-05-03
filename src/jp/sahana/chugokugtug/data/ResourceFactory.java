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
		} else if(strTagName.equals("reference")) {
			onStartReference(parser);
		}
	}

	@Override
	protected void onEndTag(XmlPullParser parser) {
		String strTagName = parser.getName();
		if(strTagName.equals("resource")) {
			onEndResource(parser);			
		} else if(strTagName.equals("data")) {
			onEndData(parser);
		} else if(strTagName.equals("reference")) {
			onEndReference(parser);
		}
	}

	protected String getName() {
		return this.name;
	}
	
	protected abstract void onStartResource(XmlPullParser parser);
	protected abstract void onEndResource(XmlPullParser parser);
	protected abstract void onStartData(XmlPullParser parser);
	protected abstract void onEndData(XmlPullParser parser);
	protected abstract void onStartReference(XmlPullParser parser);
	protected abstract void onEndReference(XmlPullParser parser);
}
