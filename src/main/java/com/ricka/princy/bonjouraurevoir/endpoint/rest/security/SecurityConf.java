package com.ricka.princy.bonjouraurevoir.endpoint.rest.security;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.ForbiddenException;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.ReadmeMonitor;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.ReadmeMonitorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_ADMIN;
import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_COMMUNITY;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConf {
    private final AuthenticationManager authenticationManager;
    private final HandlerExceptionResolver exceptionResolver;
    //TODO: find a better way
    @Autowired private ReadmeMonitor readmeMonitor;

    public SecurityConf(
        @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver,
        AuthenticationManager authenticationManager, ReadmeMonitor readmeMonitor
    ) {
        this.exceptionResolver = exceptionResolver;
        this.authenticationManager = authenticationManager;
        this.readmeMonitor = readmeMonitor;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var anonymousPath =
            new OrRequestMatcher(
                new AntPathRequestMatcher("/**", OPTIONS.name()),
                new AntPathRequestMatcher("/500", GET.name()),
                new AntPathRequestMatcher("/400", GET.name()),
                new AntPathRequestMatcher("/403", GET.name()),
                new AntPathRequestMatcher("/webhook", POST.name()),
                new AntPathRequestMatcher("/ping", GET.name()));
        http
            .exceptionHandling(
                (exceptionHandler) ->
                    exceptionHandler
                        .authenticationEntryPoint(
                            (req, res, e) -> exceptionResolver.resolveException(req, res, null, new ForbiddenException(e.getMessage())))
                        .accessDeniedHandler(
                            (req, res, e) -> exceptionResolver.resolveException(req, res, null, new ForbiddenException(e.getMessage()))))
            .addFilterBefore(
                apiKeyAuthFilter(new NegatedRequestMatcher(anonymousPath)), AnonymousAuthenticationFilter.class)
            .addFilterAfter(
                readmeMonitorFilter(new NegatedRequestMatcher(anonymousPath)), AnonymousAuthenticationFilter.class)
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth-> auth
                .requestMatchers(anonymousPath)
                .permitAll()
                .requestMatchers(GET, "/community")
                .hasAnyAuthority(ROLE_ADMIN.name(), ROLE_COMMUNITY.name())
                .requestMatchers(POST, "/admin")
                .hasAuthority(ROLE_ADMIN.name())
            );
        return http.build();
    }

    private ApiKeyAuthFilter apiKeyAuthFilter(RequestMatcher requestMatcher) {
        ApiKeyAuthFilter apiKeyFilter = new ApiKeyAuthFilter(requestMatcher);
        apiKeyFilter.setAuthenticationManager(authenticationManager);
        apiKeyFilter.setAuthenticationSuccessHandler(
            (httpServletRequest, httpServletResponse, authentication) -> {});
        apiKeyFilter.setAuthenticationFailureHandler(
            (req, res, e) -> exceptionResolver.resolveException(req, res, null, new ForbiddenException(e.getMessage())));
        return apiKeyFilter;
    }

    private ReadmeMonitorFilter readmeMonitorFilter(RequestMatcher requestMatcher) {
        return new ReadmeMonitorFilter(readmeMonitor, requestMatcher);
    }
}