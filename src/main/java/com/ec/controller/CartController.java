package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.entity.CartEntity;
import com.ec.services.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Cart APIs",
        description = "Operations related to customer shopping cart management"
)
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @Operation(
            summary = "Add Product To Cart",
            description = "Adds a product to the customer's shopping cart"
    )
    @PostMapping
    public CartEntity addToCart(
            @RequestBody CartEntity cart) {

        return service.addToCart(cart);
    }

    @Operation(
            summary = "Get Cart By User",
            description = "Returns all cart items of a specific user"
    )
    @GetMapping("/user/{userId}")
    public List<CartEntity> getCartByUser(
            @PathVariable Long userId) {

        return service.getCartByUser(userId);
    }

    @Operation(
            summary = "Remove Cart Item",
            description = "Removes a product from the shopping cart"
    )
    @DeleteMapping("/{id}")
    public String removeCartItem(
            @PathVariable Long id) {

        return service.removeCartItem(id);
    }

    @Operation(
            summary = "Update Cart Item",
            description = "Updates the quantity or details of a cart item"
    )
    @PutMapping("/{id}")
    public CartEntity updateCart(
            @PathVariable Long id,
            @RequestBody CartEntity cart) {

        return service.updateCart(id, cart);
    }

}