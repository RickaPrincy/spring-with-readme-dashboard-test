package com.ricka.princy.bonjouraurevoir.endpoint.rest.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenException extends ApiException{
    public ForbiddenException() {
        super("Forbidden", FORBIDDEN);
    }
    public ForbiddenException(String message) {
        super(message, FORBIDDEN);
    }
}
