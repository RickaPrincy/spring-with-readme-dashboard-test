package com.ricka.princy.bonjouraurevoir.endpoint.rest.controller;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.model.User;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.AuthProvider;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SuccessController {
    private final AuthProvider authProvider;

    @GetMapping("community")
    Principal community(){
        return authProvider.getPrincipal();
    }

    @PostMapping("admin")
    User post(@RequestBody User user){
        return user;
    }
}