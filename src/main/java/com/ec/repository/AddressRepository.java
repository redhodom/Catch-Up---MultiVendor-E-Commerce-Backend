package com.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

	List<AddressEntity> findByUserId(Long userId);
}
