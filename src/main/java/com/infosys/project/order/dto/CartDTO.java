package com.infosys.project.order.dto;


public class CartDTO {

	Long buyerId;

	Long prodId;

	Long quantity;

	@Override
	public String toString() {
		return "CartDTO [buyerId=" + buyerId + ", prodId=" + prodId + ", quantity=" + quantity + "]";
	}

	public CartDTO() {
		// TODO Auto-generated constructor stub
	}

	public CartDTO(Long buyerId, Long prodId, Long quantity) {
		this.buyerId = buyerId;
		this.prodId = prodId;
		this.quantity = quantity;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
