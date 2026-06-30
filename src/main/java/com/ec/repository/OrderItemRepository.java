package com.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

	List<OrderItemEntity> findByOrderId(Long orderId);
	
	List<OrderItemEntity> findByProductSellerId(Long sellerId);
	
}
