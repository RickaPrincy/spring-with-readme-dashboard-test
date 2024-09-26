package com.ricka.princy.bonjouraurevoir.endpoint.rest.controller;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class SuccessController {
    @GetMapping("hello/{name}")
    String hello(@PathVariable String name){
        return "Hello " + name;
    }

    @PostMapping("user/{name}")
    User post(@RequestBody User user){
        return user;
    }
}
