package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.entity.WishlistEntity;
import com.ec.services.WishlistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Wishlist APIs",
        description = "Operations related to customer wishlist management"
)
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService service;

    @Operation(
            summary = "Add Product To Wishlist",
            description = "Adds a product to a customer's wishlist"
    )
    @PostMapping("/{userId}/{productId}")
    public WishlistEntity addWishlist(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        return service.addWishlist(
                userId,
                productId);
    }

    @Operation(
            summary = "Get Wishlist By User",
            description = "Returns all wishlist items of a specific user"
    )
    @GetMapping("/user/{userId}")
    public List<WishlistEntity> getWishlistByUser(
            @PathVariable Long userId) {

        return service.getWishlistByUser(userId);
    }

    @Operation(
            summary = "Remove Wishlist Item",
            description = "Removes a product from the user's wishlist"
    )
    @DeleteMapping("/{id}")
    public String removeWishlistItem(
            @PathVariable Long id) {

        return service.removeWishlistItem(id);
    }

}