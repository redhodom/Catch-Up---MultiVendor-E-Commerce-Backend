package com.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ec.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> findByUserId(Long userId);
	
	List<OrderEntity> findByUserEmail(String email);
	
	@Query("""
			SELECT DISTINCT o
			FROM OrderEntity o
			JOIN o.orderItems i
			WHERE i.product.seller.email = :email
			""")
			List<OrderEntity> findOrdersBySellerEmail(
			        @Param("email") String email);
	 
	List<OrderEntity> findAll();
	
}
