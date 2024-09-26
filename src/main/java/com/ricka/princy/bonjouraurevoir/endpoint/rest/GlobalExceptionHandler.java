package com.ricka.princy.bonjouraurevoir.endpoint.rest;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.ApiErrorResponse;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ApiException.class})
    private ResponseEntity<ApiErrorResponse> apiException(ApiException apiException){
        return new ResponseEntity<>(
            new ApiErrorResponse(apiException.getMessage(), apiException.getStatus()),
            apiException.getStatus()
        );
    }

    @ExceptionHandler({RuntimeException.class})
    private ResponseEntity<ApiErrorResponse> apiException(RuntimeException apiException){
        return new ResponseEntity<>(
            new ApiErrorResponse(apiException.getMessage(), INTERNAL_SERVER_ERROR),
            INTERNAL_SERVER_ERROR
        );
    }
}
