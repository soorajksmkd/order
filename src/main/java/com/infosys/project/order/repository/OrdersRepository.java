package com.infosys.project.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.project.order.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	List<Orders> findByBuyerId(Long buyerId);

	Optional<Orders> findByOrderIdAndBuyerId(Long orderId, Long buyerId);

}
