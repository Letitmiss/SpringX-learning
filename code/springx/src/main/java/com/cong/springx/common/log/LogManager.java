package com.cong.springx.common.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogManager {

    @Bean(name = "debugLog")
    public Logger getDebugLogger() {
        return LoggerFactory.getLogger("debugLog");
    }

    @Bean(name = "infoLog")
    public Logger getInfoLogger() {
        return LoggerFactory.getLogger("infoLog");
    }

    @Bean(name = "systemLog")
    public Logger getSystemLogger() {
        return LoggerFactory.getLogger("systemLog");
    }

    @Bean(name = "errorLog")
    public Logger getErrorLogger() {
        return LoggerFactory.getLogger("errorLog");
    }

}
