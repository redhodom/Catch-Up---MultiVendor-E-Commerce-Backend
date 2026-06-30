package com.ec.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.dto.OrderItemResponseDTO;
import com.ec.dto.OrderResponseDTO;
import com.ec.dto.OrderStatus;
import com.ec.entity.OrderEntity;
import com.ec.exception.OrderNotFoundException;
import com.ec.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	public OrderEntity saveOrder(OrderEntity order) {
		return repo.save(order);
	}
	
	public List<OrderEntity> getAllOders(){
		return repo.findAll();
	}
	
	public OrderResponseDTO getOrderById(Long id) {

	    OrderEntity order = repo
	            .findById(id)
	            .orElseThrow(() ->
	                    new OrderNotFoundException("Order not found"));

	    return mapToDTO(order);
	}
	
	public List<OrderEntity> getOrdersByUser(Long userId){
		return repo.findByUserId(userId);
	}
	
	public OrderEntity updateOrderStatus(
	        Long id,
	        OrderStatus status) {

	    OrderEntity order =
	            repo.findById(id)
	            .orElseThrow(() ->
	            new OrderNotFoundException("Order not found"));

	    order.setStatus(status);

	    return repo.save(order);
	}
	
	public List<OrderEntity> getOrdersForSeller(
	        String email) {

	    return repo
	            .findOrdersBySellerEmail(email);
	}
	
	public List<OrderEntity> getOrdersByEmail(
	        String email) {

	    return repo.findByUserEmail(email);
	}
	
	public OrderEntity updateOrderStatusBySeller(
	        Long orderId,
	        OrderStatus status,
	        String sellerEmail) {

	    OrderEntity order = repo.findById(orderId)
	            .orElseThrow(() ->
	            new OrderNotFoundException("Order not found"));

	    boolean sellerOwnsOrder =
	            order.getOrderItems()
	                    .stream()
	                    .anyMatch(item ->
	                            item.getProduct()
	                                    .getSeller()
	                                    .getEmail()
	    
	                                    .equals(sellerEmail));
	    
	    System.out.println("Logged Seller : " + sellerEmail);

	    for (var item : order.getOrderItems()) {

	        System.out.println(
	                "Product : " +
	                item.getProduct().getProductName());

	        System.out.println(
	                "Owner : " +
	                item.getProduct()
	                        .getSeller()
	                        .getEmail());
	    }
	    

	    if (!sellerOwnsOrder) {
	        throw new RuntimeException(
	                "You cannot update this order");
	    }

	    order.setStatus(status);

	    return repo.save(order);
	}
	
	public List<OrderResponseDTO> getAllOrders() {

	    return repo.findAll()
	            .stream()
	            .map(this::mapToDTO)
	            .collect(Collectors.toList());
	}
	
	public OrderEntity adminUpdateOrderStatus(
	        Long orderId,
	        OrderStatus status) {

	    OrderEntity order =
	            repo
	                    .findById(orderId)
	                    .orElseThrow(
	                            () -> new OrderNotFoundException("Order not found"));

	    order.setStatus(status);

	    return repo.save(order);
	}
	
	private OrderResponseDTO mapToDTO(OrderEntity order) {

	    OrderResponseDTO dto = new OrderResponseDTO();

	    dto.setId(order.getId());
	    dto.setOrderDate(order.getOrderDate());
	    dto.setStatus(order.getStatus().name());
	    dto.setTotalAmount(order.getTotalAmount());

	    if (order.getUser() != null) {
	        dto.setCustomerName(order.getUser().getName());
	        dto.setCustomerEmail(order.getUser().getEmail());
	    }

	    dto.setItems(
	        order.getOrderItems()
	             .stream()
	             .map(item -> new OrderItemResponseDTO(
	                     item.getProduct().getProductName(),
	                     item.getQuantity(),
	                     item.getPrice()))
	             .collect(Collectors.toList())
	    );

	    return dto;
	}
	
}
