package com.infosys.project.order.entity;


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "productsordered")
@IdClass(ProductsOrderedId.class)
public class ProductsOrdered {

	@Id
	@Column(name = "orderid")

	Long orderId;
	
	@Id
	@Column(name = "prodid")
	Long prodId;
	
	@Column(name = "sellerid", nullable = false)
	Long sellerId;
	
	@Column(name = "quantity", nullable = false)
	Long quantity;

	@Column(name = "status", nullable = false, length = 60)
	String status;
	
	@Column(name = "price", precision = 10, scale = 2)
	BigDecimal price;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {

		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
