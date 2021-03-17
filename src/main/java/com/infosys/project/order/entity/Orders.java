package com.infosys.project.order.entity;


import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid", unique = true, nullable = false)
	Long orderId;
	
	@Column(name = "buyerid", nullable = false)
	Long buyerId;
	
	@Column(name = "amount", nullable = false, precision = 10, scale = 2)
	BigDecimal amount;
	
	@Column(name = "date")
	Date date;
	
	@Column(name = "address", length = 100 , nullable = false)
	String address;
	
	@Column(name = "status", length = 60, nullable = false)
	String status;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String String) {
		this.status = String;
	}


	
}
