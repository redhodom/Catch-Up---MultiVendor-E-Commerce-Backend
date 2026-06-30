package com.ec.dto;

import com.ec.entity.ReviewEntity;

public class ReviewResponseDTO {

    private Long id;

    private Integer rating;

    private String comment;

    private Long productId;
    private String productName;

    private Long userId;
    private String customerName;
    private String customerEmail;

    public ReviewResponseDTO(ReviewEntity review) {

        this.id = review.getId();
        this.rating = review.getRating();
        this.comment = review.getComment();

        if (review.getProduct() != null) {
            this.productId = review.getProduct().getId();
            this.productName = review.getProduct().getProductName();
        }

        if (review.getUser() != null) {
            this.userId = review.getUser().getId();
            this.customerName = review.getUser().getName();
            this.customerEmail = review.getUser().getEmail();
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}