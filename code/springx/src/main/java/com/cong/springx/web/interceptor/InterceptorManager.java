package com.cong.springx.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  /v1.0/** 拦截/v1.0所有
 * /v1.0/*\/** 拦截
 */
@Configuration
public class InterceptorManager implements WebMvcConfigurer {

    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(customInterceptor()).addPathPatterns("/v1.0/**").order(1);
                //excludePathPatterns();
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/v1.0/**").order(2);
    }

}
