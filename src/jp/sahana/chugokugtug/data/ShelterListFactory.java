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
		if(mShelter != null) {
			if(mDepth == depth) {
				mList.getArray().add(mShelter);
				mShelter = null;
			}
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
			} else if(field.equals("building_name")) {
				mShelter.setBuilding_name(mText);
			} else if(field.equals("address")) {
				mShelter.setAddress(mText);
			} else if(field.equals("L3")) {
				mShelter.setL3(mText);
			} else if(field.equals("L2")) {
				mShelter.setL2(mText);
			} else if(field.equals("L1")) {
				mShelter.setL1(mText);
			} else if(field.equals("L0")) {
				mShelter.setL0(mText);
			} else if(field.equals("source")) {
				mShelter.setSource(mText);
			} else if(field.equals("comments")) {
				mShelter.setComments(mText);
			}
		}
	}

	@Override
	protected void onStartReference(XmlPullParser parser) {
		if(mShelter != null) {
			field = getAttribute(parser, "field");
		}
		
	}

	@Override
	protected void onEndReference(XmlPullParser parser) {
		if(mShelter != null) {
			if(field.equals("shelter_type_id")) {
				mShelter.setShelter_type_id(mText);
			} else if(field.equals("shelter_service_id")) {
				mShelter.setShelter_service_id(mText);
			}
		}
	}

}
