package com.reviewservice.constants;

import lombok.Getter;

public enum ErrorCodeEnum {

	GENERIC_EXCEPTION("20000", "Something went wrong, please try later.");

	@Getter
	private final String errorCode;
	@Getter
	private final String errorMessage;

	private ErrorCodeEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
