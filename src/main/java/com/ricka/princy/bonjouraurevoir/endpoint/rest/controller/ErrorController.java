package com.ricka.princy.bonjouraurevoir.endpoint.rest.controller;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.BadRequestException;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.ForbiddenException;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.InternalServerErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @GetMapping("/400")
    void badRequest(){
        throw new BadRequestException();
    }

    @GetMapping("/500")
    void internalServerError(){
        throw new InternalServerErrorException();
    }

    @GetMapping("/403")
    void forbidden(){
        throw new ForbiddenException();
    }
}
