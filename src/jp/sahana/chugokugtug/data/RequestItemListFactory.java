package jp.sahana.chugokugtug.data;

import org.xmlpull.v1.XmlPullParser;

public class RequestItemListFactory extends ResourceFactory {
	private RequestItemList mList;
	private RequestItem mItem;
	private String mField;
	private String mReferenceField;
	
	@Override
	protected void initialize() {
		mList = new RequestItemList();
	}

	@Override
	protected BaseResponse getResponse() {
		return mList;
	}

	@Override
	protected void onStartResource(XmlPullParser parser) {
		if(this.getName().equals("req_req_item")) {
			mItem = new RequestItem();
			mItem.setCreatedBy(getAttribute(parser, "created_by"));
			mItem.setModifiedBy(getAttribute(parser, "modified_by"));
			mItem.setCreateDate(getAttribute(parser, "created_on"));
			mItem.setModifyDate(getAttribute(parser, "modified_on"));
			mItem.setUrl(getAttribute(parser, "url"));
		}
	}

	@Override
	protected void onEndResource(XmlPullParser parser) {
		if(mItem != null) {
			mList.getArray().add(mItem);
			mItem = null;
		}
	}

	@Override
	protected void onStartData(XmlPullParser parser) {
		if(mItem != null) {
			mField = getAttribute(parser, "field");
			if(mField.equals("quantity")) {
				mItem.setQuantity(convertDouble(getAttribute(parser, "value")));
			} else if(mField.equals("quantity_commit")) {
				mItem.setQuantitycommit(convertDouble(getAttribute(parser, "value")));
			} else if(mField.equals("quantity_transit")) {
				mItem.setQuantitytransit(convertDouble(getAttribute(parser, "value")));
			} else if(mField.equals("quantity_fulfil")) {
				mItem.setQuantityfulfil(convertDouble(getAttribute(parser, "value")));
			}
		}
	}

	@Override
	protected void onEndData(XmlPullParser parser) {
		if(mItem != null) {
			if(mField.equals("comments")) {
				mItem.setComments(mText);
			}
		}
	}

	@Override
	protected void onStartReference(XmlPullParser parser) {
		mReferenceField = getAttribute(parser, "field");
	}

	@Override
	protected void onEndReference(XmlPullParser parser) {
		if(mItem != null) {
			if(mReferenceField.equals("item_id")) {
				mItem.setItemId(mText);
			} else if(mReferenceField.equals("item_pack_id")) {
				mItem.setItemPackId(mText);
			}
		}
	}

}
