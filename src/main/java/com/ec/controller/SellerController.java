package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.OrderStatus;
import com.ec.entity.OrderEntity;
import com.ec.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Seller APIs",
        description = "Operations available for sellers"
)
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private OrderService orderService;

    @Operation(
            summary = "Seller Dashboard",
            description = "Returns the seller dashboard welcome message"
    )
    @GetMapping("/dashboard")
    public String sellerDashboard() {
        return "Welcome Seller";
    }

    @Operation(
            summary = "Get Seller Orders",
            description = "Returns all orders belonging to the logged-in seller"
    )
    @GetMapping("/orders")
    public List<OrderEntity> getSellerOrders() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.getOrdersForSeller(email);
    }

    @Operation(
            summary = "Update Seller Order Status",
            description = "Allows the seller to update the status of their own orders"
    )
    @PutMapping("/orders/{id}/{status}")
    public OrderEntity updateOrderStatus(
            @PathVariable Long id,
            @PathVariable OrderStatus status) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.updateOrderStatusBySeller(
                id,
                status,
                email);
    }

}