package com.infosys.project.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.project.order.dto.BuyerDTO;
import com.infosys.project.order.dto.OrdersDTO;
import com.infosys.project.order.dto.ProductsOrderedDTO;
import com.infosys.project.order.service.OrdersService;
import com.infosys.project.order.service.ProductsOrderedService;

@RestController
public class OrdersController {

	@Autowired
	OrdersService ordersService;

	@Autowired
	ProductsOrderedService productsOrderedService;

	@Value("${buyer.uri}")
	String buyerUri;
	
	@Value("${products.uri}")
	String productsUri;
	
	@Value("${productsOrdered.uri}")
	String productsOrderedUri;
	
	@GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrdersDTO> getAllOrders() {
		List<OrdersDTO> ordersDTOList = ordersService.getAllOrders();
		for(OrdersDTO ordersDTO : ordersDTOList) {
			List<ProductsOrderedDTO> productsOrderedDTOs = productsOrderedService.getProductsOrderedByOrderId(ordersDTO.getOrderId());
			ordersDTO.setProductsOrderedList(productsOrderedDTOs);
		}
		return ordersDTOList;
	}

	@GetMapping(value = "/orders/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrdersDTO getOrdersByOrderId(@PathVariable Long orderId) {
		OrdersDTO ordersDTO = ordersService.getOrdersByOrderId(orderId);
		if (ordersDTO==null)
			return null;
		List<ProductsOrderedDTO> productsOrderedDTOs = productsOrderedService.getProductsOrderedByOrderId(orderId);
		ordersDTO.setProductsOrderedList(productsOrderedDTOs);
		return ordersDTO;
	}

	@GetMapping(value = "/orders/buyer/{buyerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrdersDTO> getOrdersByBuyerId(@PathVariable Long buyerId) {
		List<OrdersDTO> ordersDTOList = ordersService.getOrdersByBuyerId(buyerId);
		
		for(OrdersDTO ordersDTO : ordersDTOList) {
			List<ProductsOrderedDTO> productsOrderedDTOs = productsOrderedService.getProductsOrderedByOrderId(ordersDTO.getOrderId());
			ordersDTO.setProductsOrderedList(productsOrderedDTOs);
		}
		return ordersDTOList;

	}
	
	@PostMapping(value = "/reOrder/{buyerId}/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> reOrder(@PathVariable Long buyerId, @PathVariable Long orderId){
		RestTemplate restTemplate = new RestTemplate();
		BuyerDTO buyerDTO = restTemplate.getForObject(buyerUri+"/"+buyerId, BuyerDTO.class);
		if (buyerDTO==null)
			return new ResponseEntity<>("Buyer does not exist", HttpStatus.OK);
		OrdersDTO ordersDTO = ordersService.getOrdersByOrderIdAndBuyerId(orderId, buyerId);
		if (ordersDTO==null)
			return new ResponseEntity<>("No such order exist for you", HttpStatus.OK);
		else {
			Long newOrderId = ordersService.placeReOrder(ordersDTO);
			List<ProductsOrderedDTO> productsOrderedDTO = productsOrderedService.getProductsOrderedByOrderId(orderId);
			String message = productsOrderedService.placeReOrders(productsOrderedDTO, newOrderId);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/order/{buyerId}/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<String> placeOrder(@PathVariable Long buyerId,@PathVariable Long orderId,@RequestBody OrdersDTO orderDTO){
	   String message=ordersService.placeOrder(buyerId,orderId,orderDTO);
	   return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
