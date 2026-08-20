package org.example.spring_mvc.exception;


import org.example.spring_mvc.common.response.ErrorResponse;
import org.example.spring_mvc.util.APIResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        ErrorResponse error = APIResponseUtil.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(error);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> handlerAllNotFoundException(CustomException e, HttpServletRequest request) {
        ErrorResponse error = e.getErrorResponse();
        error.setPath(request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        ErrorResponse error = APIResponseUtil.error(
                HttpStatus.BAD_REQUEST,
                "The request body is malformed. Please check the JSON format and try again.",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HashMap<String, String> errors = new HashMap<>();
        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            errors.put(field.getField(), field.getDefaultMessage());
        }

        ErrorResponse errorResponse = APIResponseUtil.error(
                HttpStatus.BAD_REQUEST,
                "Invalid Request",
                errors,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(HttpServletRequest request) {
        ErrorResponse errorResponse = APIResponseUtil.error(
                HttpStatus.UNAUTHORIZED,
                "UNAUTHORIZED",
                "Access denied",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(errorResponse);
    }

//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ErrorResponse> handleJwtException(JwtException e, HttpServletRequest request) {
//        ErrorResponse errorResponse = APIResponseUtil.error(
//                HttpStatus.UNAUTHORIZED,
//                "Invalid token.",
//                request.getRequestURI()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(errorResponse);
//    }
}