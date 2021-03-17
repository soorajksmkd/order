package com.infosys.project.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.project.order.entity.ProductsOrdered;
import com.infosys.project.order.entity.ProductsOrderedId;

public interface ProductsOrderedRepository extends JpaRepository<ProductsOrdered, ProductsOrderedId> {

	List<ProductsOrdered> findBySellerId(Long sellerId);

	Optional<ProductsOrdered> findOneBySellerIdAndOrderId(Long sellerId, Long orderId);

	List<ProductsOrdered> findByOrderId(Long orderId);

	Optional<ProductsOrdered> findByOrderIdAndProdId(Long orderId, Long prodId);

}
