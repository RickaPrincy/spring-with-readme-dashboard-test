package com.ricka.princy.bonjouraurevoir.endpoint.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/ping")
    String pong(){
        return "pong";
    }
}
