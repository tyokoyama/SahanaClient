package jp.sahana.chugokugtug.data;

import org.xmlpull.v1.XmlPullParser;

public class RequestListFactory extends ResourceFactory {
	private RequestList mList;
	private Request mRequest;
	private int mDepth;
	private String mField;
	private static final int COMMENT_DEPTH = 3;				// Comments取得用
	
	@Override
	protected void onStartResource(XmlPullParser parser) {
		if(this.getName().equals("req_req")) {
			mDepth = parser.getDepth();
			mRequest = new Request();
			mRequest.setCreatedBy(getAttribute(parser, "created_by"));
			mRequest.setModifiedBy(getAttribute(parser, "modified_by"));
			mRequest.setCreateDate(getAttribute(parser, "created_on"));
			mRequest.setModifyDate(getAttribute(parser, "modified_on"));
			mRequest.setUrl(getAttribute(parser, "url"));
		}

	}

	@Override
	protected void onEndResource(XmlPullParser parser) {
		if(mRequest != null) {
			int iDepth = parser.getDepth();
			if(iDepth == mDepth) {
				mList.getArray().add(mRequest);
				mRequest = null;
			}
		}
	}

	@Override
	protected void onStartData(XmlPullParser parser) {
		if(mRequest != null) {
			mField = getAttribute(parser, "field");
			if(mField.equals("date")) {
				mRequest.setDate(getAttribute(parser, "value"));
			} else if(mField.equals("type")) {
				mRequest.setType(convertInt(getAttribute(parser, "value")));
			} else if(mField.equals("priority")) {
				mRequest.setPriority(convertInt(getAttribute(parser, "value")));
			} else if(mField.equals("commit_status")) {
				mRequest.setCommitStatus(convertInt(getAttribute(parser, "value")));
			} else if(mField.equals("transit_status")) {
				mRequest.setTransitStatus(convertInt(getAttribute(parser, "value")));
			} else if(mField.equals("fulfil_status")) {
				mRequest.setFulfilStatus(convertInt(getAttribute(parser, "value")));
			} else if(mField.equals("cancel")) {
				String value = getAttribute(parser, "value");
				mRequest.setCancel((value != null && value.equals("true")));
			}
		}
	}

	@Override
	protected void onEndData(XmlPullParser parser) {
		if(mRequest != null) {
			if(mField.equals("type")) {
				mRequest.setTypeName(mText);
			} else if(mField.equals("commit_status")) {
				mRequest.setCommitStatusName(mText);
			} else if(mField.equals("transit_status")) {
				mRequest.setTransitStatusName(mText);
			} else if(mField.equals("fulfil_status")) {
				mRequest.setFulfilStatusName(mText);
			} else if(mField.equals("cancel")) {
				mRequest.setCancelName(mText);
			} else if(mField.equals("comments")) {
				int iDepth = parser.getDepth();
				if(iDepth == COMMENT_DEPTH) {
					// commentsはreq_req_itemでも同じタグが出てくるため、階層で判定する。
					mRequest.setComments(mText);
				}
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

	@Override
	protected void initialize() {
		mList = new RequestList();
	}

	@Override
	protected BaseResponse getResponse() {
		return mList;
	}

}
