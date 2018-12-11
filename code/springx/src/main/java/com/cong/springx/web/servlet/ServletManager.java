package com.cong.springx.web.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletManager {

    private static final String[] URL_MAPPINGS = {"/v1.x/*"};

    @Bean
    public CustomServlet customServlet () {
        return new CustomServlet();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean () {
        ServletRegistrationBean customServlet= new ServletRegistrationBean();
        customServlet.setServlet(customServlet());
        customServlet.addUrlMappings(URL_MAPPINGS);
        customServlet.setName("customServlet");
        return customServlet;
    }
}
