package com.reviewservice.utils;

import org.springframework.stereotype.Component;

import com.reviewservice.dtos.ReviewDto;
import com.reviewservice.models.Booking;
import com.reviewservice.models.Review;
import com.reviewservice.repositories.BookingRepository;

@Component
public class ReviewMapper {

    private final BookingRepository bookingRepository;

    public ReviewMapper(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Method to convert Review model to ReviewDto
    public static ReviewDto modelToDto(Review review) {
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
}
