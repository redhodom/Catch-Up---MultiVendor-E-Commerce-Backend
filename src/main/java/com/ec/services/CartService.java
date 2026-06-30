package com.ec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.entity.CartEntity;
import com.ec.entity.ProductEntity;
import com.ec.entity.UserEntity;
import com.ec.exception.ProductNotFoundException;
import com.ec.exception.UserNotFoundException;
import com.ec.repository.CartRepository;
import com.ec.repository.ProductRepository;
import com.ec.repository.UserRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository repo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;

	public CartEntity addToCart(CartEntity cart){

	    Long userId = cart.getUser().getId();
	    Long productId = cart.getProduct().getId();

	    UserEntity user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User Not Found"));

	    ProductEntity product = productRepo.findById(productId)
	            .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

	    cart.setUser(user);
	    cart.setProduct(product);

	    return repo.save(cart);
	}
	
	public List<CartEntity> getCartByUser(Long userId){
		return repo.findByUserId(userId);
	}
	
	public String removeCartItem(Long id) {
		repo.deleteById(id);
		return "Item Removed";
	}
	
	public CartEntity updateCart(Long id, CartEntity cart) {
		
		CartEntity existingCart = repo.findById(id).orElse(null);
		
		if(existingCart != null) {
			existingCart.setQuantity(cart.getQuantity());
			return repo.save(existingCart);
		}
		
		return null;
	}
	
}
