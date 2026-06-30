package com.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
	List<ProductEntity> findBySellerEmail(String email);
	
	List<ProductEntity> findByCategory(String category);
	
	List<ProductEntity> findByProductNameContaining(String keyword);
	
	List<ProductEntity> findBySellerId(Long sellerId);
	
	long countBySellerId(Long sellerId);
}
