package com.reviewservice.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateReviewRequest {
	private String content;
	private Double rating;
	private Long bookingId;
}