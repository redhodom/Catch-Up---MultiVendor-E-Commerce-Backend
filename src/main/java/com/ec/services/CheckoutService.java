package com.ec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.dto.OrderStatus;
import com.ec.entity.CartEntity;
import com.ec.entity.OrderEntity;
import com.ec.entity.OrderItemEntity;
import com.ec.entity.ProductEntity;
import com.ec.entity.UserEntity;
import com.ec.exception.UserNotFoundException;
import com.ec.repository.CartRepository;
import com.ec.repository.OrderItemRepository;
import com.ec.repository.OrderRepository;
import com.ec.repository.ProductRepository;
import com.ec.repository.UserRepository;

@Service
public class CheckoutService {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public OrderEntity checkout(Long UserId) {
		
		UserEntity user =
			    userRepo.findById(UserId)
			    .orElseThrow(() ->
			        new UserNotFoundException("User Not Found"));
		
		List<CartEntity> cartItem = cartRepo.findByUserId(UserId);
		
		double total = 0;
		
		for(CartEntity cart : cartItem) {
			
			total += cart.getProduct().getPrice() * cart.getQuantity();
		}
		
		OrderEntity order = new OrderEntity();
		
		order.setUser(user);
		order.setStatus(OrderStatus.PENDING);
		order.setTotalAmount(total);
		
		order = orderRepo.save(order);
		
		for(CartEntity cart : cartItem) {
			
			OrderItemEntity item = new OrderItemEntity();
			
			item.setOrder(order);
			item.setProduct(cart.getProduct());
			item.setQuantity(cart.getQuantity());
			item.setPrice(cart.getProduct().getPrice());
			
			orderItemRepo.save(item);
			
			 ProductEntity product =
		                cart.getProduct();

		        product.setStock(
		                product.getStock()
		                - cart.getQuantity());

		        productRepo.save(product);
		    }

		    cartRepo.deleteAll(cartItem);

		    return order;
		}
			
		
	}
	

