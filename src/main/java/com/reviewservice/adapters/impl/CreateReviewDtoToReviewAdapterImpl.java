package com.reviewservice.adapters.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.reviewservice.adapters.CreateReviewDtoToReviewAdapter;
import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.models.Booking;
import com.reviewservice.models.Review;
import com.reviewservice.repositories.BookingRepository;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter {

	private BookingRepository bookingRepository;

	public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@Override
	public Review convertDto(CreateReviewDto createReviewDto) {
		Optional<Booking> booking = bookingRepository.findById(createReviewDto.getBookingId());
		return booking.map(value -> Review.builder()
				.rating(createReviewDto.getRating())
				.booking(value)
				.content(createReviewDto.getContent())
				.build())
				.orElse(null);
	}
}