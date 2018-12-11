package com.cong.springx.web.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterManager {

    private static final String[] FILTER_PATTERNS = {"/v1.0/*", "/v1.1/*"};

    @Bean
    public FilterRegistrationBean CustomFilter() {
        FilterRegistrationBean customFilter = new FilterRegistrationBean();
        customFilter.setFilter(customFilter());
        customFilter.addUrlPatterns(FILTER_PATTERNS);
        customFilter.setName("customFiler");
        customFilter.setOrder(1);
        return  customFilter;
    }

    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }
}
