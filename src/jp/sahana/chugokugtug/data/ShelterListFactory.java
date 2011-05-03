package jp.sahana.chugokugtug.data;

import org.xmlpull.v1.XmlPullParser;

public class ShelterListFactory extends ResourceFactory {
	private ShelterList mList;
	private Shelter mShelter;
	private int mDepth;
	private String field;
	
	@Override
	protected void initialize() {
		mList = new ShelterList();
	}

	@Override
	protected BaseResponse getResponse() {
		return mList;
	}

	@Override
	protected void onStartResource(XmlPullParser parser) {
		if(this.getName().equals("cr_shelter")) {
			mDepth = parser.getDepth();
			mShelter = new Shelter();
			mShelter.setCreatedBy(getAttribute(parser, "created_by"));
			mShelter.setCreateDate(getAttribute(parser, "created_on"));
			mShelter.setModifiedBy(getAttribute(parser, "modified_by"));
			mShelter.setModifyDate(getAttribute(parser, "modified_on"));
			mShelter.setUrl(getAttribute(parser, "url"));
		}
	}

	@Override
	protected void onEndResource(XmlPullParser parser) {
		int depth = parser.getDepth();
		if(mDepth == depth) {
			mList.getArray().add(mShelter);
			mShelter = null;
		}
	}

	@Override
	protected void onStartData(XmlPullParser parser) {
		if(mShelter != null) {
			field = getAttribute(parser, "field");
			if(field.equals("capacity")) {
				mShelter.setCapacity(convertInt(getAttribute(parser, "value")));
			}
		}
	}

	@Override
	protected void onEndData(XmlPullParser parser) {
		if(mShelter != null) {
			if(field.equals("name")) {
				mShelter.setName(mText);
			}
		}
	}

	@Override
	protected void onStartReference(XmlPullParser parser) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	protected void onEndReference(XmlPullParser parser) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
