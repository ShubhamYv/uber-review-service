package com.reviewservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reviewservice.models.Review;
import com.reviewservice.repositories.ReviewRepository;
import com.reviewservice.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public Optional<Review> findReviewById(Long id) {
		return reviewRepository.findById(id);
	}

	@Override
	public List<Review> finalAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public boolean deleteReviewById(Long id) {
		try {
			reviewRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}