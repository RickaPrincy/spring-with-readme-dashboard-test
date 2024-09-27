package com.ricka.princy.bonjouraurevoir.repository;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_ADMIN;
import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_COMMUNITY;

@Repository
public class UserRepository {
    private static final List<User> USERS = List.of(
        User.builder().apiKey("adminApiKey").name("admin").role(ROLE_ADMIN).build(),
        User.builder().apiKey("communityApiKey1").name("community1").role(ROLE_COMMUNITY).build(),
        User.builder().apiKey("communityApiKey2").name("community2").role(ROLE_COMMUNITY).build()
    );

    public Optional<User> findByApiKey(String apiKey){
        return USERS.stream().filter(user -> user.apiKey().equals(apiKey)).findFirst();
    }
}
