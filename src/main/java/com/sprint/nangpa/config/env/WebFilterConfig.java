package com.sprint.nangpa.config.env;

import com.sprint.nangpa.config.security.jwt.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean JwtFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {

        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthenticationFilter);

        filterRegistrationBean.addUrlPatterns("/oauth/login");
        return filterRegistrationBean;
    }

}
