package com.reviewservice.adapters;

import com.reviewservice.dtos.CreateReviewDto;
import com.reviewservice.models.Review;

public interface CreateReviewDtoToReviewAdapter {

	public Review convertDto(CreateReviewDto createReviewDto);
}