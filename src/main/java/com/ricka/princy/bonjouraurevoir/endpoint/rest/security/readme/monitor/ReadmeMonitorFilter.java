package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static java.time.Instant.now;

@Slf4j
@RequiredArgsConstructor
public class ReadmeMonitorFilter extends HttpFilter {
    private final ReadmeMonitor readmeMonitor;

    @Override
    @SneakyThrows
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
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