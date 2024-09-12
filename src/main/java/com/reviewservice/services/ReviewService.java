package com.reviewservice.services;

import java.util.List;
import java.util.Optional;

import com.reviewservice.models.Review;

public interface ReviewService {

	public Optional<Review> findReviewById(Long id);

	public List<Review> findAllReviews();

	public boolean deleteReviewById(Long id);
	
    public Review publishReview(Review review);

    public Review updateReview(Long id, Review review);
}
