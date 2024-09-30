package com.reviewservice.utils;

import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.pojo.CreateReviewRequest;

public class ReviewRequestMapper {

    // Method to convert CreateReviewDto to CreateReviewRequest
    public static CreateReviewRequest dtoToPojo(CreateReviewDto createReviewDto) {
        if (createReviewDto == null) {
            return null;
        }

        return CreateReviewRequest.builder()
                .content(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
                .bookingId(createReviewDto.getBookingId())
                .build();
    }

    // Method to convert CreateReviewRequest to CreateReviewDto
    public static CreateReviewDto pojoToDto(CreateReviewRequest createReviewRequest) {
        if (createReviewRequest == null) {
            return null;
        }

        return CreateReviewDto.builder()
                .content(createReviewRequest.getContent())
                .rating(createReviewRequest.getRating())
                .bookingId(createReviewRequest.getBookingId())
                .build();
    }
}
