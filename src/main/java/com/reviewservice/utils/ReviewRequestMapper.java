package com.reviewservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entityservice.models.Booking;
import com.entityservice.models.Review;
import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.pojo.CreateReviewRequest;
import com.reviewservice.pojo.ReviewReqRes;
import com.reviewservice.repositories.BookingRepository;

@Component
public class ReviewRequestMapper {
	
	@Autowired
	private BookingRepository bookingRepository;

    // Method to convert CreateReviewDto to CreateReviewRequest
    public ReviewReqRes dtoToPojo(CreateReviewDto createReviewDto) {
        if (createReviewDto == null) {
            return null;
        }

        return ReviewReqRes.builder()
                .content(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
                .booking(createReviewDto.getBookingId())
                .build();
    }

    // Method to convert CreateReviewRequest to CreateReviewDto
    public CreateReviewDto pojoToDto(CreateReviewRequest createReviewRequest) {
        if (createReviewRequest == null) {
            return null;
        }

        return CreateReviewDto.builder()
                .content(createReviewRequest.getContent())
                .rating(createReviewRequest.getRating())
                .bookingId(createReviewRequest.getBookingId())
                .build();
    }

 // Method to convert CreateReviewDto to Review Model
    public Review dtoToModel(CreateReviewDto createReviewDto) {
        if (createReviewDto == null) {
            return null;
        }

        Review review = Review.builder()
                .content(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
                .build();

        if (createReviewDto.getBookingId() != null) {
            Booking booking = bookingRepository.findById(createReviewDto.getBookingId())
                    .orElse(null); 
            review.setBooking(booking);
        }

        return review;
    }
    
}