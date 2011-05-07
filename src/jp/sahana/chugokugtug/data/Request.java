package jp.sahana.chugokugtug.data;

public class Request {
	private String createDate;
	private String modifyDate;
	private String createdBy;
	private String modifiedBy;
	private String url;

	private String date;
	private String dateRequired;
	private int type;
	private String typeName;
	private int priority;
	private int commitStatus;
	private String commitStatusName;
	private int transitStatus;
	private String transitStatusName;
	private int fulfilStatus;
	private String fulfilStatusName;
	private boolean cancel;
	private String cancelName;
	private String comments;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDateRequired() {
		return dateRequired;
	}
	public void setDateRequired(String dateRequired) {
		this.dateRequired = dateRequired;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getCommitStatus() {
		return commitStatus;
	}
	public void setCommitStatus(int commitStatus) {
		this.commitStatus = commitStatus;
	}
	public String getCommitStatusName() {
		return commitStatusName;
	}
	public void setCommitStatusName(String commitStatusName) {
		this.commitStatusName = commitStatusName;
	}
	public int getTransitStatus() {
		return transitStatus;
	}
	public void setTransitStatus(int transitStatus) {
		this.transitStatus = transitStatus;
	}
	public String getTransitStatusName() {
		return transitStatusName;
	}
	public void setTransitStatusName(String transitStatusName) {
		this.transitStatusName = transitStatusName;
	}
	public int getFulfilStatus() {
		return fulfilStatus;
	}
	public void setFulfilStatus(int fulfilStatus) {
		this.fulfilStatus = fulfilStatus;
	}
	public String getFulfilStatusName() {
		return fulfilStatusName;
	}
	public void setFulfilStatusName(String fulfilStatusName) {
		this.fulfilStatusName = fulfilStatusName;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	public String getCancelName() {
		return cancelName;
	}
	public void setCancelName(String cancelName) {
		this.cancelName = cancelName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
