package com.reviewservice.utils;

import com.reviewservice.dtos.ReviewDto;
import com.reviewservice.pojo.ReviewReqRes;

public class ReviewResponseMapper {

    // Method to convert ReviewDto to ReviewResponse
    public ReviewReqRes dtoToResponse(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }

        return ReviewReqRes.builder()
                .id(reviewDto.getId())
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .booking(reviewDto.getBooking())
                .createdAt(reviewDto.getCreatedAt())
                .updatedAt(reviewDto.getUpdatedAt())
                .build();
    }
}
