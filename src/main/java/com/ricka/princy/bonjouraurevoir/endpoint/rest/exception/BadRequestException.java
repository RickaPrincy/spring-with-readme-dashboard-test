package com.ricka.princy.bonjouraurevoir.endpoint.rest.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends ApiException{
    public BadRequestException() {
        super("Bad request", BAD_REQUEST);
    }

    public BadRequestException(String message){
        super(message, BAD_REQUEST);
    }
}
