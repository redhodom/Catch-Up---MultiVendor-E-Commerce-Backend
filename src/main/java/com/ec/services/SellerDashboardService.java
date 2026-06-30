package com.ec.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.dto.SellerDashboardDTO;
import com.ec.entity.OrderItemEntity;
import com.ec.repository.OrderItemRepository;
import com.ec.repository.ProductRepository;

@Service
public class SellerDashboardService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;	
	
	public SellerDashboardDTO getDashboard(Long sellerId) {
		
		SellerDashboardDTO dto = new SellerDashboardDTO();
		
		dto.setTotalProduct(productRepo.countBySellerId(sellerId));
		
		List<OrderItemEntity> items = orderItemRepo.findByProductSellerId(sellerId);

        dto.setTotalOrders((long) items.size());

        double revenue = 0;
        
        Set<Long> customers = new HashSet<>();

        for(OrderItemEntity item : items) {

            revenue +=
                    item.getPrice() *
                    item.getQuantity();

            customers.add(
                    item.getOrder()
                    .getUser()
                    .getId());
        }

        dto.setTotalRevenue(revenue);

        dto.setTotalCustomers(
                (long) customers.size());

        return dto;
		
	}
	
}
