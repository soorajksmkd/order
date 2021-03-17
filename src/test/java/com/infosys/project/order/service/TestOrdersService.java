package com.infosys.project.order.service;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.infosys.project.order.dto.OrdersDTO;
import com.infosys.project.order.entity.Orders;
import com.infosys.project.order.repository.OrdersRepository;
import com.infosys.project.order.repository.ProductsOrderedRepository;

@ExtendWith(MockitoExtension.class)
class TestOrdersService {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Mock
	OrdersRepository ordersRepository;
	
	@Mock
	ProductsOrderedRepository productsOrderedRepository;
	
	@InjectMocks
	OrdersService ordersService= new OrdersService();
	
	@InjectMocks
	ProductsOrderedService productsOrderedService;
	
	@Test
	public void authenticateOrdersByBuyerIdValid() throws Exception{
		
		Orders order1 = new Orders();
		order1.setBuyerId(1L);
		order1.setOrderId(1L);
		order1.setAmount(BigDecimal.valueOf(2000.0));
		order1.setAddress("HYD");
		order1.setStatus("ORDER_PLACED");
		order1.setDate(Date.valueOf("2020-01-28"));
		
		Orders order2 = new Orders();
		order2.setBuyerId(2L);
		order2.setOrderId(5L);
		order2.setAmount(BigDecimal.valueOf(2000.0));
		order2.setAddress("Delhi");
		order2.setStatus("ORDER_PLACED");
		order2.setDate(Date.valueOf("2020-01-28"));
		
		List<Orders> ordersList = new ArrayList<Orders>();
		ordersList.add(order1);
		ordersList.add(order2);
		
		Mockito.when(ordersRepository.findByBuyerId(Mockito.anyLong())).thenReturn(ordersList);
		
		List<OrdersDTO> listOrders = ordersService.getOrdersByBuyerId(order1.getBuyerId());
		List<Orders> orders = new ArrayList<>();
		for(OrdersDTO orderDTO: listOrders) {
			Orders order = orderDTO.createEntity();
			orders.add(order);
		}
	
		for(int i=0;i<ordersList.size();i++) {
			Assertions.assertEquals(orders.get(i).getBuyerId(), ordersList.get(i).getBuyerId());
			Assertions.assertEquals(orders.get(i).getOrderId(), ordersList.get(i).getOrderId());
			Assertions.assertEquals(orders.get(i).getAddress(), ordersList.get(i).getAddress());
			Assertions.assertEquals(orders.get(i).getAmount(), ordersList.get(i).getAmount());
			Assertions.assertEquals(orders.get(i).getDate(), ordersList.get(i).getDate());
			Assertions.assertEquals(orders.get(i).getStatus(), ordersList.get(i).getStatus());
		}
	}
	
	@Test
	public void authenticateOrdersByBuyerIdInvalid() throws Exception{
		
		OrdersDTO orderDTO1 = new OrdersDTO();
		orderDTO1.setBuyerId(1L);
		orderDTO1.setOrderId(11L);
		orderDTO1.setAmount(BigDecimal.valueOf(2000.0));
		orderDTO1.setAddress("HYD");
		orderDTO1.setStatus("ORDER_PLACED");
		orderDTO1.setDate(Date.valueOf("2020-01-28"));
		
		OrdersDTO orderDTO2 = new OrdersDTO();
		orderDTO2.setBuyerId(2L);
		orderDTO2.setOrderId(12L);
		orderDTO2.setAmount(BigDecimal.valueOf(999.0));
		orderDTO2.setAddress("Delhi");
		orderDTO2.setStatus("ORDER_PLACED");
		orderDTO2.setDate(Date.valueOf("2020-02-28"));
		
		Orders order1 = orderDTO1.createEntity();
	
		
		List<Orders> ordersList = new ArrayList<Orders>();
	
		Mockito.when(ordersRepository.findByBuyerId(Mockito.anyLong())).thenReturn(ordersList);
		List<OrdersDTO> listOrders = ordersService.getOrdersByBuyerId(order1.getBuyerId());
		//Exception exception=Assertions.assertThrows(Exception.class, ()->ordersService.getOrdersByBuyerId(order1.getBuyerId()));
		for(int i=0; i<listOrders.size();i++) {
			Assertions.assertEquals(null, listOrders.get(i).getBuyerId());
			Assertions.assertEquals(null, listOrders.get(i).getOrderId());
			Assertions.assertEquals(null, listOrders.get(i).getAddress());
			Assertions.assertEquals(null, listOrders.get(i).getAmount());
			Assertions.assertEquals(null, listOrders.get(i).getDate());
			Assertions.assertEquals(null, listOrders.get(i).getStatus());
		}
		
	}
	
	@Test
	public void authenticateOrdersByOrderIdValid() throws Exception{
		
		Orders order1 = new Orders();
		order1.setBuyerId(1L);
		order1.setOrderId(1L);
		order1.setAmount(BigDecimal.valueOf(2000.0));
		order1.setAddress("HYD");
		order1.setStatus("ORDER_PLACED");
		order1.setDate(Date.valueOf("2020-01-28"));
		
		Orders order2 = new Orders();
		order2.setBuyerId(2L);
		order2.setOrderId(5L);
		order2.setAmount(BigDecimal.valueOf(2000.0));
		order2.setAddress("Delhi");
		order2.setStatus("ORDER_PLACED");
		order2.setDate(Date.valueOf("2020-01-28"));
		
		List<Orders> ordersList = new ArrayList<Orders>();
		ordersList.add(order1);
		ordersList.add(order2);
		Optional<Orders> optional = Optional.of(order1);
		
		Mockito.when(ordersRepository.findById(Mockito.anyLong())).thenReturn(optional);
				
		OrdersDTO listOrders = ordersService.getOrdersByOrderId(order1.getOrderId());
		Orders order = new Orders();
		order = listOrders.createEntity();
		

		Assertions.assertEquals(order.getBuyerId(), order1.getBuyerId());
		Assertions.assertEquals(order.getOrderId(), order1.getOrderId());	
		Assertions.assertEquals(order.getAmount(), order1.getAmount());	
		Assertions.assertEquals(order.getAddress(), order1.getAddress());	
		Assertions.assertEquals(order.getDate(), order1.getDate());	
		Assertions.assertEquals(order.getStatus(), order1.getStatus());	
		
	}
	
	@Test
	public void authenticateOrdersByOrderIdInvalid() throws Exception{
		
		OrdersDTO orderDTO1 = new OrdersDTO();
		orderDTO1.setBuyerId(1L);
		orderDTO1.setOrderId(11L);
		orderDTO1.setAmount(BigDecimal.valueOf(2000.0));
		orderDTO1.setAddress("HYD");
		orderDTO1.setStatus("ORDER_PLACED");
		orderDTO1.setDate(Date.valueOf("2020-01-28"));
		
		OrdersDTO orderDTO2 = new OrdersDTO();
		orderDTO2.setBuyerId(2L);
		orderDTO2.setOrderId(12L);
		orderDTO2.setAmount(BigDecimal.valueOf(999.0));
		orderDTO2.setAddress("Delhi");
		orderDTO2.setStatus("ORDER_PLACED");
		orderDTO2.setDate(Date.valueOf("2020-02-28"));
		
		Orders order1 = orderDTO1.createEntity();
	
		Optional<Orders> optional = Optional.empty();
		
		Mockito.when(ordersRepository.findById(Mockito.anyLong())).thenReturn(optional);
		OrdersDTO listOrders = ordersService.getOrdersByOrderId(order1.getOrderId());
		//Exception exception=Assertions.assertThrows(Exception.class, ()->ordersService.getOrdersByOrderId(order1.getOrderId()));
		Assertions.assertEquals(null, listOrders);
	}

}
