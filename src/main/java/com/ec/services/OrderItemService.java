package com.ec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.entity.OrderEntity;
import com.ec.entity.OrderItemEntity;
import com.ec.entity.ProductEntity;
import com.ec.exception.OrderNotFoundException;
import com.ec.exception.ProductNotFoundException;
import com.ec.repository.OrderItemRepository;
import com.ec.repository.OrderRepository;
import com.ec.repository.ProductRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public OrderItemEntity saveOrder(OrderItemEntity item) {

	    Long orderId = item.getOrder().getId();
	    Long productId = item.getProduct().getId();

	    OrderEntity order = orderRepo.findById(orderId)
	            .orElseThrow(() -> new OrderNotFoundException("Order Not Found"));

	    ProductEntity product = productRepo.findById(productId)
	            .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

	    if(product.getStock() < item.getQuantity()) {
	        throw new RuntimeException("Insufficient Stock");
	    }

	    product.setStock(
	            product.getStock() - item.getQuantity());

	    productRepo.save(product);

	    item.setOrder(order);
	    item.setProduct(product);
	    item.setPrice(product.getPrice());

	    return repo.save(item);
	}
	
	public List<OrderItemEntity> getOrderItems(Long orderId){
	    return repo.findByOrderId(orderId);
	}
	
}
