package com.infosys.project.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.project.order.dto.ProductsOrderedDTO;
import com.infosys.project.order.entity.Orders;
import com.infosys.project.order.entity.ProductsOrdered;
import com.infosys.project.order.repository.OrdersRepository;
import com.infosys.project.order.repository.ProductsOrderedRepository;
import com.infosys.project.order.validator.StatusValidator;

@Service
@Transactional
public class ProductsOrderedService {

	@Autowired
	ProductsOrderedRepository productsOrderedRepository;

	@Autowired
	OrdersRepository ordersRepository;

	public List<ProductsOrderedDTO> getAllProductsOrdered() {
		List<ProductsOrdered> productsOrdered = productsOrderedRepository.findAll();
		List<ProductsOrderedDTO> productsOrderedDTOs = new ArrayList<>();

		for (ProductsOrdered productOrdered : productsOrdered) {
			ProductsOrderedDTO productsOrderedDTO = ProductsOrderedDTO.valueOf(productOrdered);
			productsOrderedDTOs.add(productsOrderedDTO);
		}
		return productsOrderedDTOs;
	}
	public List<ProductsOrderedDTO> getProductsOrderedBySellerId(Long sellerId) {

		List<ProductsOrdered> productsOrdered = productsOrderedRepository.findBySellerId(sellerId);
		List<ProductsOrderedDTO> productsOrderedDTOs = new ArrayList<>();

		for (ProductsOrdered productOrdered : productsOrdered) {
			ProductsOrderedDTO productsOrderedDTO = ProductsOrderedDTO.valueOf(productOrdered);
			productsOrderedDTOs.add(productsOrderedDTO);
		}
		return productsOrderedDTOs;
	}

	public boolean changeOrderStatus(Long orderId, Long prodId, String status) {
		
		Optional<ProductsOrdered> optionalProductsOrdered = productsOrderedRepository.findByOrderIdAndProdId(orderId,
				prodId);
		Optional<Orders> optOrders = ordersRepository.findById(orderId);
		Boolean flag = StatusValidator.validateStatus(status);
		if (!flag)
			return false;
		if (optionalProductsOrdered.isPresent()) {
			ProductsOrdered productsOrdered = optionalProductsOrdered.get();
			if(optOrders.isPresent())
			{
				Orders orders = optOrders.get();
				orders.setStatus(status);
				productsOrdered.setStatus(status);
				ordersRepository.save(orders);
				productsOrderedRepository.save(productsOrdered);
				return true;
			}
			
		}
		return false;
	}

	public List<ProductsOrderedDTO> getProductsOrderedByOrderId(Long orderId) {
		

		List<ProductsOrdered> productsOrdered = productsOrderedRepository.findByOrderId(orderId);
		List<ProductsOrderedDTO> productsOrderedDTOs = new ArrayList<>();

		for (ProductsOrdered productOrdered : productsOrdered) {
			ProductsOrderedDTO productsOrderedDTO = ProductsOrderedDTO.valueOf(productOrdered);
			productsOrderedDTOs.add(productsOrderedDTO);
		}
		return productsOrderedDTOs;
	}

	public ProductsOrderedDTO getProductsOrderedByOrderIdAndProductId(Long orderId, Long prodId) {
		Optional<ProductsOrdered> optionalProductsOrdered = productsOrderedRepository.findByOrderIdAndProdId(orderId,
				prodId);
		if (optionalProductsOrdered.isPresent()) {
			ProductsOrdered productsOrdered = optionalProductsOrdered.get();
			ProductsOrderedDTO productsOrderedDTO = ProductsOrderedDTO.valueOf(productsOrdered);
			return productsOrderedDTO;
		}
		return null;
	}

	public String placeReOrders(List<ProductsOrderedDTO> productsOrderedDTOList, Long newOrderId) {
		for (ProductsOrderedDTO productsOrderedDTO : productsOrderedDTOList) {
			productsOrderedDTO.setOrderId(newOrderId);
			productsOrderedDTO.setStatus("Placed");
			ProductsOrdered productsOrdered = productsOrderedDTO.createEntity();
			productsOrderedRepository.save(productsOrdered);
		}
		return "Products re-ordered succesfully";

		
		
	}


}
