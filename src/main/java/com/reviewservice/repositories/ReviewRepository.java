package com.reviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reviewservice.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
	