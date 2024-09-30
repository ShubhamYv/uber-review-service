package com.reviewservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entityservice.models.Booking;
import com.entityservice.models.Review;
import com.reviewservice.dtos.ReviewDto;
import com.reviewservice.pojo.ReviewReqRes;
import com.reviewservice.repositories.BookingRepository;

@Component
public class ReviewMapper {

	@Autowired
    private BookingRepository bookingRepository;

    public ReviewMapper(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Method to convert Review model to ReviewDto
    public ReviewDto modelToDto(Review review) {
        if (review == null) {
            return null;
        }

        return ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .booking(review.getBooking() != null ? review.getBooking().getId() : null)
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    // Method to convert ReviewDto to Review model
    public Review dtoToModel(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }

        Review review = Review.builder()
                .id(reviewDto.getId())
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .createdAt(reviewDto.getCreatedAt())
                .updatedAt(reviewDto.getUpdatedAt())
                .build();

        if (reviewDto.getBooking() != null) {
            Booking booking = bookingRepository.findById(reviewDto.getBooking())
                    .orElse(null); 
            review.setBooking(booking);
        }
        
        return review;
    }

    // Method to convert ReviewDto to ReviewReqRes (POJO)
    public ReviewReqRes dtoToPojo(ReviewDto reviewDto) {
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

    // Method to convert ReviewReqRes (POJO) to ReviewDto
    public ReviewDto pojoToDto(ReviewReqRes reviewReqRes) {
        if (reviewReqRes == null) {
            return null;
        }

        return ReviewDto.builder()
                .id(reviewReqRes.getId())
                .content(reviewReqRes.getContent())
                .rating(reviewReqRes.getRating())
                .booking(reviewReqRes.getBooking())
                .createdAt(reviewReqRes.getCreatedAt())
                .updatedAt(reviewReqRes.getUpdatedAt())
                .build();
    }
}