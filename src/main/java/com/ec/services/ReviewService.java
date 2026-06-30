package com.ec.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.dto.ReviewResponseDTO;
import com.ec.entity.ProductEntity;
import com.ec.entity.ReviewEntity;
import com.ec.entity.UserEntity;
import com.ec.exception.ReviewNotFoundException;
import com.ec.repository.ProductRepository;
import com.ec.repository.ReviewRepository;
import com.ec.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repo;
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    public ReviewResponseDTO saveReview(ReviewEntity review) {

        ReviewEntity saved = repo.save(review);

        UserEntity user = userRepo.findById(
                saved.getUser().getId())
                .orElseThrow();

        ProductEntity product = productRepo.findById(
                saved.getProduct().getId())
                .orElseThrow();

        saved.setUser(user);
        saved.setProduct(product);

        return new ReviewResponseDTO(saved);
    }
    public List<ReviewResponseDTO> getReviewByProduct(Long productId) {

        return repo.findByProductId(productId)
                .stream()
                .map(ReviewResponseDTO::new)
                .collect(Collectors.toList());
    }

    public String deleteReview(Long id) {

        ReviewEntity review = repo.findById(id)
                .orElseThrow(() ->
                        new ReviewNotFoundException("Review Not Found"));

        repo.delete(review);

        return "Review Deleted";
    }

}