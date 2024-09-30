package com.reviewservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.dtos.ReviewDto;
import com.reviewservice.models.Review;
import com.reviewservice.repositories.ReviewRepository;
import com.reviewservice.services.ReviewService;
import com.reviewservice.utils.ReviewMapper;
import com.reviewservice.utils.ReviewRequestMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRequestMapper requestMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, ReviewRequestMapper requestMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.requestMapper = requestMapper;
    }

    @Override
    public ReviewDto findReviewById(Long id) throws EntityNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with id " + id + " not found"));
        return reviewMapper.modelToDto(review);
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(reviewMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteReviewById(Long id) {
        if (!reviewRepository.existsById(id)) {
            return false;
        }

        reviewRepository.deleteById(id);
        return true; 
    }

    @Override
    @Transactional
    public ReviewDto publishReview(CreateReviewDto incomingReview) {
        Review review = requestMapper.dtoToModel(incomingReview);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.modelToDto(savedReview);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto newReviewData) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review with id " + id + " not found"));

        if (newReviewData.getRating() != null) {
            review.setRating(newReviewData.getRating());
        }
        if (newReviewData.getContent() != null) {
            review.setContent(newReviewData.getContent());
        }

        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.modelToDto(updatedReview);
    }
}
