package com.ricka.princy.bonjouraurevoir.endpoint.rest.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalServerErrorException extends ApiException{
    public InternalServerErrorException(String message) {
        super(message, INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(){
        super("Internal Server Error", INTERNAL_SERVER_ERROR);
    }
}
