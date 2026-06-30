package com.ec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.entity.ProductEntity;
import com.ec.entity.UserEntity;
import com.ec.entity.WishlistEntity;
import com.ec.exception.ProductNotFoundException;
import com.ec.exception.UserNotFoundException;
import com.ec.repository.ProductRepository;
import com.ec.repository.UserRepository;
import com.ec.repository.WishlistRepository;

@Service
public class WishlistService {

	
	@Autowired
	private WishlistRepository repo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;
	
	public List<WishlistEntity> getWishlistByUser(Long userid) {
		return repo.findByUserId(userid);
	}
	
	public String removeWishlistItem(Long id) {
		repo.deleteById(id);
		return "WishList Item Removed";
	}
	
	public WishlistEntity addWishlist(
	        Long userId,
	        Long productId) {

	    if(repo.existsByUserIdAndProductId(
	            userId,
	            productId)) {

	        throw new RuntimeException(
	                "Already in wishlist");
	    }

	    UserEntity user =
	    		userRepo.findById(userId)
	    		.orElseThrow(() ->
	    		new UserNotFoundException("User Not Found"));

	    ProductEntity product =
	    		productRepo.findById(productId)
	    		.orElseThrow(() ->
	    		new ProductNotFoundException("Product Not Found"));

	    WishlistEntity wish =
	            new WishlistEntity();

	    wish.setUser(user);
	    wish.setProduct(product);

	    return repo.save(wish);
	}
	
}
