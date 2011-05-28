package jp.sahana.chugokugtug.put;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Log;
import android.util.Xml;

public abstract class BasePutDataFactory {
	private XmlSerializer serializer;
	public String createXml() {
		serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			
			// 各データの追加
			create(serializer);
			
			serializer.endDocument();
		} catch(Exception e) {
			Log.d("SahanaClient", "Exception:", e);
		}
		
		return writer.toString();
	}
		
	// データのXMLへの変換処理
	protected abstract void create(XmlSerializer serializer) throws IOException;	
}
