package jp.sahana.chugokugtug.put;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

public class PutRequestItemFactory extends BasePutDataFactory {
	private PutRequestItem mItem;
	
	public PutRequestItemFactory(PutRequestItem item) {
		mItem = item;
	}
	
	@Override
	protected void create(XmlSerializer serializer) throws IOException {
		if(mItem != null) {
			serializer.startTag("", "s3xml");
			
			serializer.startTag("", "resource");
			serializer.attribute("", "name", "req_req_item");
			
			serializer.startTag("", "data");
			serializer.attribute("", "field", "req_id");
			serializer.attribute("", "value", String.valueOf(mItem.getReqId()));
			serializer.endTag("", "data");
			
			serializer.startTag("", "data");
			serializer.attribute("", "field", "req_item_id");
			serializer.attribute("", "value", String.valueOf(mItem.getReqItemId()));
			serializer.endTag("", "data");
			
			serializer.startTag("", "data");
			serializer.attribute("", "field", "req_item_pack_id");
			serializer.attribute("", "value", String.valueOf(mItem.getReqitemPackId()));
			serializer.endTag("", "data");			

			serializer.startTag("", "data");
			serializer.attribute("", "field", "quantity");
			serializer.attribute("", "value", String.valueOf(mItem.getQuantity()));
			serializer.endTag("", "data");			

			serializer.startTag("", "data");
			serializer.attribute("", "field", "comments");
			serializer.attribute("", "value", mItem.getComments());
			serializer.endTag("", "data");			

			serializer.endTag("", "resource");
			
			serializer.endTag("", "s3xml");
		}
	}

}
