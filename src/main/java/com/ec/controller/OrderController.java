package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.OrderResponseDTO;
import com.ec.dto.OrderStatus;
import com.ec.entity.OrderEntity;
import com.ec.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Order APIs",
        description = "Operations related to customer orders"
)
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @Operation(
            summary = "Create Order",
            description = "Creates a new order"
    )
    @PostMapping
    public OrderEntity saveOrder(
            @RequestBody OrderEntity order) {

        return service.saveOrder(order);
    }

    @Operation(
            summary = "Get All Orders",
            description = "Returns all orders"
    )
    @GetMapping
    public List<OrderEntity> getAllOrders() {

        return service.getAllOders();
    }

    @Operation(
            summary = "Get Order By ID",
            description = "Returns a specific order using its ID"
    )
    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(
            @PathVariable Long id) {

        return service.getOrderById(id);
    }

    @Operation(
            summary = "Get Orders By User",
            description = "Returns all orders placed by a specific user"
    )
    @GetMapping("/user/{userId}")
    public List<OrderEntity> getOrdersByUser(
            @PathVariable Long userId) {

        return service.getOrdersByUser(userId);
    }

    @Operation(
            summary = "Update Order Status",
            description = "Updates the status of an existing order"
    )
    @PutMapping("/{id}/{status}")
    public OrderEntity updateOrderStatus(
            @PathVariable Long id,
            @PathVariable OrderStatus status) {

        return service.updateOrderStatus(id, status);
    }

    @Operation(
            summary = "My Orders",
            description = "Returns all orders of the currently logged-in customer"
    )
    @GetMapping("/my-orders")
    public List<OrderEntity> myOrders() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return service.getOrdersByEmail(email);
    }

}