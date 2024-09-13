package com.reviewservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateReviewDto {
	private String content;
	private Double rating;
	private Long bookingId;
}