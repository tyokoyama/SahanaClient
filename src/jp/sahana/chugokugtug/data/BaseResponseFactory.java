package jp.sahana.chugokugtug.data;

import java.io.StringReader;

import jp.sahana.chugokugtug.data.BaseResponse;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;

public abstract class BaseResponseFactory {
	protected String mText;
	
	public BaseResponse create(String strResponse) {
		
		initialize();

		parse(strResponse);
		
		return getResponse();
	}
	
	private void parse(String strResponse) {
		if(strResponse != null && strResponse.length() > 0) {			
			try {
				XmlPullParser parser = Xml.newPullParser();
				parser.setInput(new StringReader(strResponse));
				
				int parseType;
				while((parseType = parser.next()) != XmlPullParser.END_DOCUMENT) {
					switch(parseType) {
					case XmlPullParser.START_TAG:
						onStartTag(parser);
						break;
					case XmlPullParser.TEXT:
						mText = parser.getText();
						break;
					case XmlPullParser.END_TAG:
						onEndTag(parser);
						break;
					}
				}
				
			} catch(Exception e) {
				Log.d("TEST", "Exception:", e);
			}
			
		}
	}
	
	/* 型変換などに利用するメソッド */
	protected int convertInt(String strValue) {
		int iRet = 0;
		if(strValue != null) {
			iRet = Integer.parseInt(strValue);
		}
		return iRet;
	}
	
	protected long convertLong(String strValue) {
		long lRet = 0;
		if(strValue != null) {
			lRet = Long.parseLong(strValue);
		}
		return lRet;
	}
	
	protected double convertDouble(String strValue) {
		double dRet = 0.0;
		if(strValue != null) {
			dRet = Double.parseDouble(strValue);
		}
		return dRet;
	}
	
	protected String getAttribute(XmlPullParser parser, String strName) {
		String strRet = null;
		for(int i = 0; i < parser.getAttributeCount(); i++) {
			if(parser.getAttributeName(i).equals(strName)) {
				strRet = parser.getAttributeValue(i);
			}
		}
		
		return strRet;
	}
	
	protected abstract void initialize();
	protected abstract void onStartTag(XmlPullParser parser);
	protected abstract void onEndTag(XmlPullParser parser);
	protected abstract BaseResponse getResponse();
}
