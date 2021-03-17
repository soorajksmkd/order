package com.infosys.project.order.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.infosys.project.order.entity.Orders;

public class OrdersDTO {

	Long orderId;
	
	Long buyerId;
	
	BigDecimal amount;
	
	Date date;
	
	String status;
	
	String address;
	
	List<ProductsOrderedDTO> productsOrdered;

	public List<ProductsOrderedDTO> getProductsOrderedList() {
		return productsOrdered;
	}

	public void setProductsOrderedList(List<ProductsOrderedDTO> productsOrderedList) {
		this.productsOrdered = productsOrderedList;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	//converts Entity to DTO
		public static OrdersDTO valueOf(Orders orders) {
			OrdersDTO ordersDTO = new OrdersDTO();
			
			ordersDTO.setOrderId(orders.getOrderId());
			ordersDTO.setAddress(orders.getAddress());
			ordersDTO.setAmount(orders.getAmount());
			ordersDTO.setDate(orders.getDate());
			ordersDTO.setStatus(orders.getStatus());
			ordersDTO.setBuyerId(orders.getBuyerId());
			return ordersDTO;
			
		}
		
		//converts DTO to Entity
		public Orders createEntity() {
			Orders orders = new Orders();
			
			orders.setAddress(this.getAddress());
			orders.setAmount(this.getAmount());
			orders.setDate(this.getDate());
			orders.setOrderId(this.getOrderId());
			orders.setStatus(this.getStatus());
			orders.setBuyerId(this.getBuyerId());
			
			return orders;
			
		}

		public OrdersDTO(Long orderId, Long buyerId, BigDecimal amount, Date date, String status, String address,
				List<ProductsOrderedDTO> productsOrderedList) {
			this.orderId = orderId;
			this.buyerId = buyerId;
			this.amount = amount;
			this.date = date;
			this.status = status;
			this.address = address;
			this.productsOrdered = productsOrderedList;
		}

		public OrdersDTO() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "OrdersDTO [orderId=" + orderId + ", buyerId=" + buyerId + ", amount=" + amount + ", date=" + date
					+ ", status=" + status + ", address=" + address + ", productsOrderedList=" + productsOrdered
					+ "]";
		}
		

}
