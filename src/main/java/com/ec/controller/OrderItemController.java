package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.entity.OrderItemEntity;
import com.ec.services.OrderItemService;

@RestController
@RequestMapping("/ordersitems")
public class OrderItemController {
	
	@Autowired
	private OrderItemService service;

	@PostMapping
	public OrderItemEntity saveOrderItem(@RequestBody OrderItemEntity item) {
		return service.saveOrder(item);
	}
	
	@GetMapping("/{orderId}")
	public List<OrderItemEntity> getOrderItems(@PathVariable Long orderId){
		return service.getOrderItems(orderId);
	}
	
}
