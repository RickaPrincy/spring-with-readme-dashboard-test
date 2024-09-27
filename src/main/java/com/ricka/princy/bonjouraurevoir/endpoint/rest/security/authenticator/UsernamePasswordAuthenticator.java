package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.authenticator;

import java.util.Objects;
import java.util.Set;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Principal;
import com.ricka.princy.bonjouraurevoir.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticator {
    private final UserRepository userRepository;

    public UserDetails retrieveUser(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        String candidateApiKey = getApiKeyFromHeader(usernamePasswordAuthenticationToken);
        var connectedUser = userRepository.findByApiKey(candidateApiKey).orElseThrow(() -> new BadCredentialsException("Bad Credentials"));
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