package com.reviewservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reviewservice.constants.ErrorCodeEnum;
import com.reviewservice.pojo.ErrorResponse;

@ControllerAdvice
public class UberReviewServiceExceptionHandler {

    @ExceptionHandler(UberReviewServiceException.class)
    public ResponseEntity<ErrorResponse> handleUberAuthException(UberReviewServiceException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getErrorMessage())
                .build();
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(ErrorCodeEnum.GENERIC_EXCEPTION.getErrorCode())
                .errorMessage(ErrorCodeEnum.GENERIC_EXCEPTION.getErrorMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
