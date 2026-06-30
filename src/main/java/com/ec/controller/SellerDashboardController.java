package com.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.SellerDashboardDTO;
import com.ec.services.SellerDashboardService;

@RestController
@RequestMapping("/seller")
public class SellerDashboardController {

    @Autowired
    private SellerDashboardService service;

    @GetMapping("/dashboard/{sellerId}")
    public SellerDashboardDTO getDashboard(@PathVariable Long sellerId){
        return service.getDashboard(sellerId);
    }
}