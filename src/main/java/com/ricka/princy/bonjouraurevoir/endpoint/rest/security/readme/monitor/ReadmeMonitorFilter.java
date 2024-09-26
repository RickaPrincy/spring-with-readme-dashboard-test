package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static java.time.Instant.now;

@Slf4j
@RequiredArgsConstructor
public class ReadmeMonitorFilter implements Filter {
    private final ReadmeMonitor readmeMonitor;

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        var startedDatetime = now();
        Exception exception = null;
        try{
            filterChain.doFilter(request, response);
        }catch(Exception error){
            exception = error;
        }
        var requestDuration = Duration.between(startedDatetime, now()).toMillis();
        readmeMonitor.saveLog(request, response, requestDuration);
        if(exception != null){
            log.info("Exception founded {}", exception.toString());
            throw exception;
        }
    }
}