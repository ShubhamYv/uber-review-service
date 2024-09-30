package com.reviewservice.utils;

import com.reviewservice.dtos.ReviewDto;
import com.reviewservice.pojo.ReviewResponse;

public class ReviewResponseMapper {

    // Method to convert ReviewDto to ReviewResponse
    public static ReviewResponse dtoToResponse(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }

        return ReviewResponse.builder()
                .id(reviewDto.getId())
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .booking(reviewDto.getBooking())
                .createdAt(reviewDto.getCreatedAt())
                .updatedAt(reviewDto.getUpdatedAt())
                .build();
    }

    // Method to convert ReviewResponse to ReviewDto
    public static ReviewDto responseToDto(ReviewResponse reviewResponse) {
        if (reviewResponse == null) {
            return null;
        }

        return ReviewDto.builder()
                .id(reviewResponse.getId())
                .content(reviewResponse.getContent())
                .rating(reviewResponse.getRating())
                .booking(reviewResponse.getBooking())
                .createdAt(reviewResponse.getCreatedAt())
                .updatedAt(reviewResponse.getUpdatedAt())
                .build();
    }
}
