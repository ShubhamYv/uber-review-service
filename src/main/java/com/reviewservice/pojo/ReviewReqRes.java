package com.reviewservice.pojo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewReqRes {
	private Long id;
	private String content;
	private Double rating;
	private Long booking;
	private Date createdAt;
	private Date updatedAt;
}