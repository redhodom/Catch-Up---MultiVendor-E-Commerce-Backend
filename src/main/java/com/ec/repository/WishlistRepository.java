package com.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.entity.WishlistEntity;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {

	
	boolean existsByUserIdAndProductId(
	        Long userId,
	        Long productId);
	
	WishlistEntity findByUserIdAndProductId(
	        Long userId,
	        Long productId);
	
	List<WishlistEntity> findByUserId(Long userId);
	
}
