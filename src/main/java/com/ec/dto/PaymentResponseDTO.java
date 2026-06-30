package com.ec.dto;

import java.time.LocalDateTime;

import com.ec.entity.PaymentEntity;

public class PaymentResponseDTO {

    private Long id;
    private Long orderId;

    private String customerName;
    private String customerEmail;

    private Double amount;

    private String paymentMethod;
    private String paymentStatus;

    private LocalDateTime paymentDate;

    public PaymentResponseDTO(PaymentEntity payment) {

        this.id = payment.getId();

        this.amount = payment.getAmount();

        this.paymentMethod = payment.getPaymentMethod();

        this.paymentStatus = payment.getPaymentStatus();

        this.paymentDate = payment.getPaymentDate();

        if(payment.getOrder() != null) {

            this.orderId = payment.getOrder().getId();

            if(payment.getOrder().getUser() != null) {

                this.customerName =
                        payment.getOrder().getUser().getName();

                this.customerEmail =
                        payment.getOrder().getUser().getEmail();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
}