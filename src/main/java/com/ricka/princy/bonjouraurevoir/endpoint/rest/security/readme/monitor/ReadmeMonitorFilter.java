package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import static java.time.Instant.now;

@Slf4j
@RequiredArgsConstructor
public class ReadmeMonitorFilter extends OncePerRequestFilter {
    private final ReadmeMonitor readmeMonitor;
    private final RequestMatcher requestMatcher;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) {
        if(!requestMatcher.matches(request)){
            filterChain.doFilter(request, response);
            return;
        }

        var startedDatetime = now();
        Exception exception = null;
        try{
            filterChain.doFilter(request, response);
        }catch(Exception error){
            exception = error;
        }
        var endedDatetime = now();
        readmeMonitor.saveLog(request, response, startedDatetime, endedDatetime);

        if(exception != null){
            log.info("Exception founded {}", exception.toString());
            throw exception;
        }
    }
}