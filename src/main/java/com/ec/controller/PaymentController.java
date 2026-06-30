package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.PaymentResponseDTO;
import com.ec.entity.PaymentEntity;
import com.ec.services.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Payment APIs",
        description = "Operations related to order payments"
)
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Operation(
            summary = "Create Payment",
            description = "Creates a new payment for an order"
    )
    @PostMapping
    public PaymentResponseDTO savePayment(
            @RequestBody PaymentEntity payment) {

        return service.savePayment(payment);
    }

    @Operation(
            summary = "Get Payments By Order",
            description = "Returns all payments associated with a specific order"
    )
    @GetMapping("/order/{orderId}")
    public List<PaymentResponseDTO> getPaymentsById(
            @PathVariable Long orderId) {

        return service.getPaytmentByOrder(orderId);
    }

    @Operation(
            summary = "Update Payment Status",
            description = "Updates the payment status of a payment"
    )
    @PutMapping("/{id}/{status}")
    public PaymentResponseDTO updatePaymentStatus(
            @PathVariable Long id,
            @PathVariable String status) {

        return service.updatePaymentStatus(id, status);
    }

}