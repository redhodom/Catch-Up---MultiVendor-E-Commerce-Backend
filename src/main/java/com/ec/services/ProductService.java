package com.ec.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ec.dto.ProductResponseDTO;
import com.ec.entity.ProductEntity;
import com.ec.entity.UserEntity;
import com.ec.exception.ProductNotFoundException;
import com.ec.exception.UserNotFoundException;
import com.ec.repository.ProductRepository;
import com.ec.repository.UserRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public ProductEntity saveProduct(
	        ProductEntity product) {

	    String email = SecurityContextHolder
	            .getContext()
	            .getAuthentication()
	            .getName();

	    UserEntity seller =
	            userRepo.findByEmail(email)
	            .orElseThrow(() ->
	            new UserNotFoundException("Seller Not Found"));

	    product.setSeller(seller);

	    return repo.save(product);
	}
	
	public List<ProductResponseDTO> getAllProducts() {

	    return repo.findAll()
	            .stream()
	            .map(this::mapToDTO)
	            .collect(Collectors.toList());

	}
	
	public List<ProductResponseDTO> getProductsBySellerEmail(String sellerEmail){

	    return repo.findBySellerEmail(sellerEmail)
	            .stream()
	            .map(this::mapToDTO)
	            .collect(Collectors.toList());

	}

	public ProductResponseDTO getProductaById(Long id) {

	    ProductEntity product = repo.findById(id)
	            .orElseThrow(() ->
	                    new ProductNotFoundException("Product Not Found"));

	    return new ProductResponseDTO(product);
	}
	
	public ProductEntity updateProduct(
	        Long id,
	        ProductEntity product) {

	    ProductEntity existing =
	            repo.findById(id)
	            .orElseThrow(() ->
	            new ProductNotFoundException("Product Not Found"));

	    UserEntity loggedInUser =
	            getLoggedInUser();

	    boolean isAdmin =
	            loggedInUser.getRole().name()
	                    .equals("ADMIN");

	    boolean isOwner =
	            existing.getSeller()
	                    .getId()
	                    .equals(loggedInUser.getId());

	    if (!isAdmin && !isOwner) {
	        throw new RuntimeException(
	                "You cannot update this product");
	    }

	    existing.setProductName(
	            product.getProductName());

	    existing.setDescription(
	            product.getDescription());

	    existing.setPrice(
	            product.getPrice());

	    existing.setStock(
	            product.getStock());

	    existing.setCategory(
	            product.getCategory());

	    existing.setImageUrl(
	            product.getImageUrl());

	    return repo.save(existing);
	}
	
	public String deleteProduct(Long id) {

	    ProductEntity product =
	            repo.findById(id)
	            .orElseThrow(() ->
	            new ProductNotFoundException("Product Not Found"));

	    UserEntity loggedInUser =
	            getLoggedInUser();

	    boolean isAdmin =
	            loggedInUser.getRole().name()
	                    .equals("ADMIN");

	    boolean isOwner =
	            product.getSeller()
	                    .getId()
	                    .equals(loggedInUser.getId());

	    if (!isAdmin && !isOwner) {
	        throw new RuntimeException(
	                "You cannot delete this product");
	    }

	    repo.delete(product);

	    return "Product Deleted Successfully";
	}
	
	public List<ProductResponseDTO> getProductsByCategory(String category) {

	    return repo.findByCategory(category)
	            .stream()
	            .map(this::mapToDTO)
	            .collect(Collectors.toList());

	}
	
	public List<ProductResponseDTO> searchProduct(String keyword) {

	    return repo.findByProductNameContaining(keyword)
	            .stream()
	            .map(this::mapToDTO)
	            .collect(Collectors.toList());

	}
	
	public List<ProductResponseDTO> getProductBySeller(Long sellerId){

	    return repo.findBySellerId(sellerId)
	            .stream()
	            .map(this::mapToDTO)
	            .collect(Collectors.toList());

	}
	
	public void reduceStock(Long productId, int quantity) {
		
		ProductEntity product = repo.findById(productId).orElse(null);
		
		if(product != null) {
			
			if(product.getStock() < quantity) {
				throw new RuntimeException("Insufficient stock");
			}
			
			product.setStock(product.getStock() - quantity);
			
			repo.save(product);
			
		}
		
	}

	public ProductEntity saveProductForSeller(
	        ProductEntity product,
	        String email) {

	    UserEntity seller = userRepo
	            .findByEmail(email)
	            .orElseThrow(() ->
	                new RuntimeException("Seller Not Found"));

	    product.setSeller(seller);

	    return repo.save(product);
	}

	private UserEntity getLoggedInUser() {

	    String email =
	            SecurityContextHolder
	                    .getContext()
	                    .getAuthentication()
	                    .getName();

	    return userRepo.findByEmail(email)
	            .orElseThrow(() ->
	            new UserNotFoundException("User Not Found"));
	}
	
	private ProductResponseDTO mapToDTO(ProductEntity product) {

	    ProductResponseDTO dto = new ProductResponseDTO();

	    dto.setId(product.getId());
	    dto.setProductName(product.getProductName());
	    dto.setDescription(product.getDescription());
	    dto.setPrice(product.getPrice());
	    dto.setStock(product.getStock());
	    dto.setCategory(product.getCategory());
	    dto.setImageUrl(product.getImageUrl());

	    if (product.getSeller() != null) {
	        dto.setSellerName(product.getSeller().getName());
	        dto.setSellerEmail(product.getSeller().getEmail());
	    }

	    return dto;
	}
	
}
