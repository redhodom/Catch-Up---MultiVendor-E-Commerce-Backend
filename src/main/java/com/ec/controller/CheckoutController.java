package com.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.entity.OrderEntity;
import com.ec.services.CheckoutService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
    private CheckoutService service;

    @PostMapping("/{userId}")
    public OrderEntity checkout(
            @PathVariable Long userId){

        return service.checkout(userId);
    }
	
	
}
