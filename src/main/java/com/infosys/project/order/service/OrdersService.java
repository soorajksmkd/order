package com.infosys.project.order.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.project.order.dto.OrdersDTO;
import com.infosys.project.order.entity.Orders;
import com.infosys.project.order.repository.OrdersRepository;

@Service
@Transactional
public class OrdersService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrdersRepository ordersRepository;
	
	public List<OrdersDTO> getAllOrders() {
		List<Orders> orders = ordersRepository.findAll();
		List<OrdersDTO> orderDTOs = new ArrayList<>();
		for (Orders order : orders) {
			OrdersDTO orderDTO = OrdersDTO.valueOf(order);
			orderDTOs.add(orderDTO);
		}
		logger.info("Orders details : {}", orderDTOs);
		return orderDTOs;
	}

	public List<OrdersDTO> getOrdersByBuyerId(Long buyerId) {
		List<Orders> orders = ordersRepository.findByBuyerId(buyerId);
		List<OrdersDTO> orderDTOs = new ArrayList<>();
		for (Orders order : orders) {
			OrdersDTO orderDTO = OrdersDTO.valueOf(order);
			orderDTOs.add(orderDTO);
		}
		logger.info("Order details for buyerId : {}", buyerId);
		return orderDTOs;
	}

	public OrdersDTO getOrdersByOrderId(Long orderId) {
		Optional<Orders> optOrders = ordersRepository.findById(orderId);
		OrdersDTO ordersDTO = null;
		if (optOrders.isPresent()) {
			Orders orders = optOrders.get();
			ordersDTO = OrdersDTO.valueOf(orders);
		}
		logger.info("Order details for buyerId : {}", orderId);
		return ordersDTO;
	}

	public OrdersDTO getOrdersByOrderIdAndBuyerId(Long orderId, Long buyerId) {
		Optional<Orders> optOrders = ordersRepository.findByOrderIdAndBuyerId(orderId, buyerId);
		OrdersDTO ordersDTO = null;
		if (optOrders.isPresent()) {
			Orders orders = optOrders.get();
			ordersDTO = OrdersDTO.valueOf(orders);
		}
		logger.info("Order details for buyerId {} and orderId : {}", buyerId, orderId);
		return ordersDTO;
	}

	public String placeOrder(OrdersDTO ordersDTO) {
		
		return null;
	}

	public Long placeReOrder(OrdersDTO ordersDTO) {
		ordersDTO.setStatus("Placed");
		ordersDTO.setOrderId(null);
		ordersDTO.setDate(Date.valueOf(LocalDate.now()));
		Orders orders = ordersDTO.createEntity();
		ordersRepository.save(orders);
		List<Orders> ordersList = ordersRepository.findByBuyerId(ordersDTO.getBuyerId());
		Long orderId = ordersList.get(ordersList.size()-1).getOrderId();
		logger.info("The new orderId is {}", orderId);
		return orderId;
	}

	public String placeOrder(Long buyerId, Long orderId, OrdersDTO orderDTO) {
		orderDTO.setBuyerId(buyerId);
		orderDTO.setOrderId(orderId);
		Orders order=orderDTO.createEntity();
		ordersRepository.save(order);
		return "Order Placed Successfully.";
	}

}
