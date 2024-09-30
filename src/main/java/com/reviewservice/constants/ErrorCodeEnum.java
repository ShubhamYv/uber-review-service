package com.reviewservice.constants;

import lombok.Getter;

public enum ErrorCodeEnum {

    GENERIC_EXCEPTION("20000", "Something went wrong, please try later."),
    NULL_REQUEST_DTO("20001", "Request DTO cannot be null."),
    REVIEW_NOT_FOUND("20002", "Review not found."),
    DELETE_FAILED("20003", "Failed to delete review."),
    PUBLISH_FAILED("20004", "Failed to publish review."),
    UPDATE_FAILED("20005", "Failed to update review.");

    @Getter
    private final String errorCode;
    @Getter
    private final String errorMessage;

    private ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
