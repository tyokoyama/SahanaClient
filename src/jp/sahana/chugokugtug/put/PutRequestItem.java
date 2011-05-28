package jp.sahana.chugokugtug.put;

public class PutRequestItem {
	private int reqId;
	private int reqItemId;
	private int reqitemPackId;
	private int quantity;
	private String comments;
	
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	public int getReqItemId() {
		return reqItemId;
	}
	public void setReqItemId(int reqItemId) {
		this.reqItemId = reqItemId;
	}
	public int getReqitemPackId() {
		return reqitemPackId;
	}
	public void setReqitemPackId(int reqitemPackId) {
		this.reqitemPackId = reqitemPackId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
