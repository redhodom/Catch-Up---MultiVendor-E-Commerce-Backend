package com.ec.dto;

import com.ec.entity.ProductEntity;

public class ProductResponseDTO {

    private Long id;

    private String productName;

    private String description;

    private Double price;

    private Integer stock;

    private String category;

    private String imageUrl;

    private String sellerName;

    private String sellerEmail;
    
    public ProductResponseDTO() {

    }
    
    public ProductResponseDTO(ProductEntity product) {

        this.id = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.category = product.getCategory();
        this.imageUrl = product.getImageUrl();

        if(product.getSeller() != null) {
            this.sellerName = product.getSeller().getName();
            this.sellerEmail = product.getSeller().getEmail();
        }
    }


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

}