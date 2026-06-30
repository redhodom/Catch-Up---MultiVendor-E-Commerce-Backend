package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.OrderResponseDTO;
import com.ec.dto.OrderStatus;
import com.ec.entity.OrderEntity;
import com.ec.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Admin APIs",
        description = "Administrative operations for managing the system"
)
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Operation(
            summary = "Admin Dashboard",
            description = "Returns the admin dashboard welcome message"
    )
    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "Welcome Admin";
    }

    @Operation(
            summary = "Get All Orders",
            description = "Returns all orders in the system"
    )
    @GetMapping("/orders")
    public List<OrderResponseDTO> getAllOrders() {

        return orderService.getAllOrders();
    }

    @Operation(
            summary = "Get Order By ID",
            description = "Returns an order using its ID"
    )
    @GetMapping("/orders/{id}")
    public OrderResponseDTO getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    @Operation(
            summary = "Update Order Status",
            description = "Allows the administrator to update the status of any order"
    )
    @PutMapping("/orders/{id}/{status}")
    public OrderEntity updateOrderStatus(
            @PathVariable Long id,
            @PathVariable OrderStatus status) {

        return orderService.adminUpdateOrderStatus(
                id,
                status);
    }

}