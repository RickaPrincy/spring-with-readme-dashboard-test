package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.authenticator;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.model.User;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Principal;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_ADMIN;
import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_COMMUNITY;

@Component
public class UsernamePasswordAuthenticator {
    public static final List<User> users = List.of(
        User.builder().apiKey("apiKey1").name("admin").role(ROLE_ADMIN).build(),
        User.builder().apiKey("apiKey2").name("community1").role(ROLE_COMMUNITY).build(),
        User.builder().apiKey("apiKey3").name("community2").role(ROLE_COMMUNITY).build()
    );

    public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        String candidateApiKey = getApiKeyFromHeader(usernamePasswordAuthenticationToken);
        var connectedUser = users.stream().filter(user -> user.apiKey().equals(candidateApiKey)).findFirst().orElseThrow(() -> new BadCredentialsException("Bad Credentials"));
        return new Principal(candidateApiKey, Set.of(new Authority(connectedUser.role())));
    }

    private String getApiKeyFromHeader(
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        Object tokenObject = usernamePasswordAuthenticationToken.getCredentials();
        if (!(tokenObject instanceof String)
                || !Objects.equals(usernamePasswordAuthenticationToken.getName(), "x-api-key")) {
            return null;
        }
        return ((String) tokenObject);
    }
}