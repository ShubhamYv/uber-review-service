package com.reviewservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passenger_review")
@Getter
@Setter
public class PassengerReview extends Review {

	@Column(nullable = false)
	private String passengerReviewContent;

	@Column(nullable = false)
	private String passengerRating;
}
