package com.infosys.project.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.project.order.dto.ProductsOrderedDTO;
import com.infosys.project.order.service.ProductsOrderedService;

@RestController
public class ProductsOrderedController {

	@Autowired
	ProductsOrderedService productsOrderedService;

	@GetMapping(value = "/productsOrdered/seller/{sellerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsOrderedDTO> getProductsOrderedBySellerId(@PathVariable Long sellerId) {
		return productsOrderedService.getProductsOrderedBySellerId(sellerId);
	}

	@GetMapping(value = "/productsOrdered/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsOrderedDTO> getProductsOrderedByOrderId(@PathVariable Long orderId) {
		
		return productsOrderedService.getProductsOrderedByOrderId(orderId);
	}

	@PutMapping(value = "/productsOrdered/{orderId}/{prodId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changeOrderStatus(@RequestBody ProductsOrderedDTO productsOrderedDTO, @PathVariable Long orderId, @PathVariable Long prodId) {
		
		boolean flag = productsOrderedService.changeOrderStatus(orderId, prodId, productsOrderedDTO.getStatus());
		if (flag)
			return new ResponseEntity<>("Updated Status successfully", HttpStatus.OK);
		return new ResponseEntity<>("Invalid Order Status OR Product not ordered", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/productsOrdered", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsOrderedDTO> getAllProductsOrdered() {
		
		return productsOrderedService.getAllProductsOrdered();
	}
	
	@GetMapping(value = "/productsOrdered/{orderId}/{prodId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductsOrderedDTO getProductsOrderedByOrderIdAndProductId(@PathVariable Long orderId, @PathVariable Long prodId) {
		
		return productsOrderedService.getProductsOrderedByOrderIdAndProductId(orderId, prodId);
	}

}
