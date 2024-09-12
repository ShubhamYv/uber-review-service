package com.reviewservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewservice.models.Review;
import com.reviewservice.services.ReviewService;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = this.reviewService.findAllReviews();
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
		try {
			Optional<Review> review = this.reviewService.findReviewById(reviewId);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
		try {
			boolean isDeleted = this.reviewService.deleteReviewById(reviewId);
			if (!isDeleted)
				return new ResponseEntity<>("Unable to delete Review", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}