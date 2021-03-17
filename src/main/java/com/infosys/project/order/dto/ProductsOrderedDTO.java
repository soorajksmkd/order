package com.infosys.project.order.dto;

import java.math.BigDecimal;
import com.infosys.project.order.entity.ProductsOrdered;

public class ProductsOrderedDTO {

	Long orderId;
		
	Long prodId;
		
	Long sellerId;
		
	Long quantity;
	
	String status;
	
	BigDecimal price;

	public ProductsOrderedDTO() {
		
	}

	
	public ProductsOrderedDTO(Long orders, Long product, Long seller, Long quantity, String status,
			BigDecimal price) {
		this.orderId = orders;
		this.prodId = product;
		this.sellerId = seller;
		this.quantity = quantity;
		this.status = status;
		this.price = price;
	}

	

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

	public void setPrice(BigDecimal bigDecimal) {
		this.price = bigDecimal;
	}

	
	@Override
	public String toString() {
		return "ProductsOrderedDTO [orders=" + orderId + ", product=" + prodId + ", seller=" + sellerId + ", quantity="
				+ quantity + ", status=" + status + ", price=" + price + "]";
	}


	//Converts Entity to DTO
	public static ProductsOrderedDTO valueOf (ProductsOrdered productsOrdered) {
		ProductsOrderedDTO productsOrderedDTO = new ProductsOrderedDTO();
		
		productsOrderedDTO.setPrice(productsOrdered.getPrice());
		productsOrderedDTO.setQuantity(productsOrdered.getQuantity());
		productsOrderedDTO.setStatus(productsOrdered.getStatus());
		productsOrderedDTO.setProdId(productsOrdered.getProdId());
		productsOrderedDTO.setOrderId(productsOrdered.getOrderId());
		productsOrderedDTO.setSellerId(productsOrdered.getSellerId());
		return productsOrderedDTO;
	}
	
	//Converts DTO to Entity
	public ProductsOrdered createEntity() {
		ProductsOrdered productsOrdered = new ProductsOrdered();
		
		productsOrdered.setPrice(this.getPrice());
		productsOrdered.setQuantity(this.getQuantity());
		productsOrdered.setStatus(this.getStatus());
		productsOrdered.setOrderId(this.getOrderId());
		productsOrdered.setProdId(this.getProdId());
		productsOrdered.setSellerId(this.getSellerId());
		
		return productsOrdered;
	}
	
}