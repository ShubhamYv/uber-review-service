package com.reviewservice.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.reviewservice.constants.ErrorCodeEnum;
import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.dtos.ReviewDto;
import com.reviewservice.exception.UberReviewServiceException;
import com.reviewservice.models.Review;
import com.reviewservice.repositories.ReviewRepository;
import com.reviewservice.services.ReviewService;
import com.reviewservice.utils.ReviewMapper;
import com.reviewservice.utils.ReviewRequestMapper;

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
    public ReviewDto findReviewById(Long id) {
        if (id == null) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorMessage(),
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorCode(),
                HttpStatus.BAD_REQUEST
            );
        }

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new UberReviewServiceException(
                    ErrorCodeEnum.REVIEW_NOT_FOUND.getErrorMessage(),
                    ErrorCodeEnum.REVIEW_NOT_FOUND.getErrorCode(),
                    HttpStatus.NOT_FOUND
                ));
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
        if (id == null) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorMessage(),
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorCode(),
                HttpStatus.BAD_REQUEST
            );
        }

        if (!reviewRepository.existsById(id)) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.REVIEW_NOT_FOUND.getErrorMessage(),
                ErrorCodeEnum.REVIEW_NOT_FOUND.getErrorCode(),
                HttpStatus.NOT_FOUND
            );
        }

        try {
            reviewRepository.deleteById(id);
            return true; 
        } catch (Exception e) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.DELETE_FAILED.getErrorMessage(),
                ErrorCodeEnum.DELETE_FAILED.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    @Transactional
    public ReviewDto publishReview(CreateReviewDto incomingReview) {
        if (incomingReview == null) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorMessage(),
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorCode(),
                HttpStatus.BAD_REQUEST
            );
        }

        try {
            Review review = requestMapper.dtoToModel(incomingReview);
            Review savedReview = reviewRepository.save(review);
            return reviewMapper.modelToDto(savedReview);
        } catch (Exception e) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.PUBLISH_FAILED.getErrorMessage(),
                ErrorCodeEnum.PUBLISH_FAILED.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto newReviewData) {
        if (id == null || newReviewData == null) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorMessage(),
                ErrorCodeEnum.NULL_REQUEST_DTO.getErrorCode(),
                HttpStatus.BAD_REQUEST
            );
        }

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new UberReviewServiceException(
                    ErrorCodeEnum.REVIEW_NOT_FOUND.getErrorMessage(),
                    ErrorCodeEnum.REVIEW_NOT_FOUND.getErrorCode(),
                    HttpStatus.NOT_FOUND
                ));

        if (newReviewData.getRating() != null) {
            review.setRating(newReviewData.getRating());
        }
        if (newReviewData.getContent() != null) {
            review.setContent(newReviewData.getContent());
        }

        try {
            Review updatedReview = reviewRepository.save(review);
            return reviewMapper.modelToDto(updatedReview);
        } catch (Exception e) {
            throw new UberReviewServiceException(
                ErrorCodeEnum.UPDATE_FAILED.getErrorMessage(),
                ErrorCodeEnum.UPDATE_FAILED.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
