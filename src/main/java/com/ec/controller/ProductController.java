package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.ProductResponseDTO;
import com.ec.entity.ProductEntity;
import com.ec.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
		name = "Product APIs",
		description = "Opration Related to Products"
)
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Operation(
    	    summary = "Create Product",
    	    description = "Seller creates a new product"
    	)
    @PostMapping
    public ProductEntity saveProduct(@RequestBody ProductEntity product) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return service.saveProductForSeller(product, email);
    }

    @Operation(
    	    summary = "Get All Products",
    	    description = "Returns all available products"
    	)
    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @Operation(
    	    summary = "Get Product By ID",
    	    description = "Returns a single product using its ID"
    	)
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(
            @PathVariable Long id) {

        return service.getProductaById(id);
    }

    @Operation(
    	    summary = "Update Product",
    	    description = "Updates an existing product"
    	)
    @PutMapping("/{id}")
    public ProductEntity updateProduct(
            @PathVariable Long id,
            @RequestBody ProductEntity product) {

        return service.updateProduct(id, product);
    }

    @Operation(
    	    summary = "Delete Product",
    	    description = "Deletes a product by ID"
    	)
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id);
    }

    @Operation(
    	    summary = "Get Products By Category",
    	    description = "Returns all products in the specified category"
    	)
    @GetMapping("/category/{category}")
    public List<ProductResponseDTO> getProductByCategory(
            @PathVariable String category) {

        return service.getProductsByCategory(category);
    }

    @Operation(
    	    summary = "Search Products",
    	    description = "Search products using a keyword"
    	)
    @GetMapping("/search/{keyword}")
    public List<ProductResponseDTO> searchProduct(
            @PathVariable String keyword) {

        return service.searchProduct(keyword);
    }

    @Operation(
    	    summary = "Products By Seller",
    	    description = "Returns all products belonging to a seller"
    	)
    @GetMapping("/seller/{sellerId}")
    public List<ProductResponseDTO> getProductBySeller(
            @PathVariable Long sellerId) {

        return service.getProductBySeller(sellerId);
    }

    @Operation(
    	    summary = "My Products",
    	    description = "Returns all products of the currently logged-in seller"
    	)
    @GetMapping("/my-products")
    public List<ProductResponseDTO> getMyProducts() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return service.getProductsBySellerEmail(email);
    }

}