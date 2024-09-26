package com.ricka.princy.bonjouraurevoir.endpoint.rest.security;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.ReadmeMonitorFilter;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.ReadmeMonitor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@RequiredArgsConstructor
public class FilterConfig {
    private final ReadmeMonitor readmeMonitor;

//    @Bean
//    public FilterRegistrationBean<ReadmeMonitorFilter> monitoringFilter() {
//        FilterRegistrationBean<ReadmeMonitorFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new ReadmeMonitorFilter(readmeMonitor));
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(1);
//
//        return registrationBean;
//    }
}