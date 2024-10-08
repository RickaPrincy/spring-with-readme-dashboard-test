package com.ricka.princy.bonjouraurevoir.endpoint.rest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class ApiKeyAuthFilter extends AbstractAuthenticationProcessingFilter {
    protected ApiKeyAuthFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String apiKey = request.getHeader("x-api-key");
        AuthenticationManager manager = this.getAuthenticationManager();
        return manager.authenticate(new UsernamePasswordAuthenticationToken("x-api-key", apiKey));
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authenticated
    ) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authenticated);
        chain.doFilter(request, response);
    }
}