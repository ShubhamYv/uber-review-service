package com.reviewservice.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reviewservice.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Integer countAllByRatingIsLessThanEqual(Integer givenRating);

	List<Review> findAllByRatingIsLessThanEqual(Integer givenRating);

	List<Review> findAllByCreatedAtBefore(Date date);

	@Query("select r from Booking b inner join Review r where b.id = :bookingId")
	Review findReviewByBookingId(Long bookingId);

}
