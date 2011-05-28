package jp.sahana.chugokugtug.data;

public class RequestItem {
	private String createDate;
	private String modifyDate;
	private String createdBy;
	private String modifiedBy;
	private String url;

	private String itemId;
	private String itemPackId;
	private double quantity;
	private double quantitycommit;
	private double quantitytransit;
	private double quantityfulfil;
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemPackId() {
		return itemPackId;
	}
	public void setItemPackId(String itemPackId) {
		this.itemPackId = itemPackId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getQuantitycommit() {
		return quantitycommit;
	}
	public void setQuantitycommit(double quantitycommit) {
		this.quantitycommit = quantitycommit;
	}
	public double getQuantitytransit() {
		return quantitytransit;
	}
	public void setQuantitytransit(double quantitytransit) {
		this.quantitytransit = quantitytransit;
	}
	public double getQuantityfulfil() {
		return quantityfulfil;
	}
	public void setQuantityfulfil(double quantityfulfil) {
		this.quantityfulfil = quantityfulfil;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
