package org.example.spring_mvc.exception;

import org.example.spring_mvc.common.response.ErrorResponse;
import org.example.spring_mvc.util.APIResponseUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorResponse errorResponse;
    private final HttpStatus httpStatus;

    // Constructor with only HttpStatus
    public CustomException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.errorResponse = APIResponseUtil.error(
                httpStatus,
                httpStatus.getReasonPhrase()
        );
        this.httpStatus = httpStatus;
    }

    // Constructor with just message
    public CustomException(String message) {
        super(message);
        this.errorResponse = APIResponseUtil.error(
                HttpStatus.BAD_REQUEST,
                message
        );
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    // Constructor with ErrorResponse
    public CustomException(ErrorResponse errorResponse, HttpStatus httpStatus) {
        super(errorResponse.getMessage());
        this.errorResponse = APIResponseUtil.error(errorResponse);
        this.httpStatus = httpStatus;
    }

    // Constructor with HTTP status and message
    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.errorResponse = APIResponseUtil.error(
                httpStatus,
                message
        );
        this.httpStatus = httpStatus;
    }

    // Constructor with message, details, HTTP status, and path
    public CustomException(String message, String details, HttpStatus httpStatus, String path) {
        super(message);
        this.errorResponse = APIResponseUtil.error(
                httpStatus,
                message,
                details,
                path
        );
        this.httpStatus = httpStatus;
    }
}