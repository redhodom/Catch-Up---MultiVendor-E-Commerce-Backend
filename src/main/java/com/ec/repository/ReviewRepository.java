package com.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{

	List<ReviewEntity> findByProductId(Long productId);
	
}
