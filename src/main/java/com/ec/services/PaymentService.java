package com.ec.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.dto.PaymentResponseDTO;
import com.ec.entity.OrderEntity;
import com.ec.entity.PaymentEntity;
import com.ec.repository.OrderRepository;
import com.ec.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	public PaymentResponseDTO savePayment(PaymentEntity payment) {
		payment.setPaymentDate(LocalDateTime.now());

		PaymentEntity saved = repo.save(payment);

		OrderEntity order =
		        orderRepo.findById(saved.getOrder().getId())
		        .orElseThrow();

		saved.setOrder(order);

		return new PaymentResponseDTO(saved);
	}
	
	public List<PaymentResponseDTO> getPaytmentByOrder(Long orderId){

	    return repo.findByOrderId(orderId)
	            .stream()
	            .map(PaymentResponseDTO::new)
	            .collect(Collectors.toList());
	}
	
	public PaymentResponseDTO updatePaymentStatus(Long id, String status) {

	    PaymentEntity existing = repo.findById(id).orElse(null);

	    if (existing != null) {

	        existing.setPaymentStatus(status);

	        return new PaymentResponseDTO(repo.save(existing));
	    }

	    throw new RuntimeException("Payment Not Found");
	}

}
