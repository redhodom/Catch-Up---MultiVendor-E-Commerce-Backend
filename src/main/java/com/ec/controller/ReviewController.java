package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.ReviewResponseDTO;
import com.ec.entity.ReviewEntity;
import com.ec.services.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Review APIs",
        description = "Operations related to product reviews"
)
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Operation(
            summary = "Add Review",
            description = "Creates a new review for a product"
    )
    @PostMapping
    public ReviewResponseDTO saveReview(
            @RequestBody ReviewEntity review) {

        return service.saveReview(review);
    }

    @Operation(
            summary = "Get Reviews By Product",
            description = "Returns all reviews of a specific product"
    )
    @GetMapping("/product/{productId}")
    public List<ReviewResponseDTO> getReviewByProduct(
            @PathVariable Long productId) {

        return service.getReviewByProduct(productId);
    }

    @Operation(
            summary = "Delete Review",
            description = "Deletes a review using its ID"
    )
    @DeleteMapping("/{id}")
    public String deleteReview(
            @PathVariable Long id) {

        return service.deleteReview(id);
    }

}