package com.reviewservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.dtos.ReviewDto;
import com.reviewservice.pojo.CreateReviewRequest;
import com.reviewservice.pojo.ReviewReqRes;
import com.reviewservice.services.ReviewService;
import com.reviewservice.utils.ReviewRequestMapper;
import com.reviewservice.utils.ReviewMapper;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRequestMapper reviewRequestMapper;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, 
                            ReviewRequestMapper reviewRequestMapper, 
                            ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewRequestMapper = reviewRequestMapper;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping
    public ResponseEntity<ReviewReqRes> publishReview(@Validated @RequestBody CreateReviewRequest request) {
        CreateReviewDto requestDto = reviewRequestMapper.pojoToDto(request);
        ReviewDto reviewDto = reviewService.publishReview(requestDto);
        ReviewReqRes response = reviewMapper.dtoToPojo(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReviewReqRes>> getAllReviews() {
        List<ReviewDto> reviewDtos = reviewService.findAllReviews();
        List<ReviewReqRes> response = reviewDtos.stream()
                .map(reviewMapper::dtoToPojo)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewReqRes> findReviewById(@PathVariable Long reviewId) {
        ReviewDto reviewDto = reviewService.findReviewById(reviewId);
        ReviewReqRes response = reviewMapper.dtoToPojo(reviewDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReviewById(reviewId);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Unable to delete Review");
        }
        return ResponseEntity.ok("Review deleted successfully");
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewReqRes> updateReview(@PathVariable Long reviewId, @RequestBody ReviewReqRes request) {
        ReviewDto updatedDto = reviewService.updateReview(reviewId, reviewMapper.pojoToDto(request));
        ReviewReqRes response = reviewMapper.dtoToPojo(updatedDto);
        return ResponseEntity.ok(response);
    }
}
