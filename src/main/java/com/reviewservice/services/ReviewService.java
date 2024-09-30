package com.reviewservice.services;

import java.util.List;

import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.dtos.ReviewDto;

public interface ReviewService {

    ReviewDto findReviewById(Long id);

    List<ReviewDto> findAllReviews();

    boolean deleteReviewById(Long id);
    
    ReviewDto publishReview(CreateReviewDto incomingReview);

    ReviewDto updateReview(Long id, ReviewDto reviewDto);
}
