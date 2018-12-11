package com.cong.springx.web.listener;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * 自定义Listener(常用的监听器 servletContextListener、httpSessionListener、servletRequestListener)
 *
 */
@WebListener
public class CustomContextListener implements ServletContextListener {

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        INFO_LOG.info("init CustomContextListener ");
        //初始化比较早, 做一些统一资源加载

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        INFO_LOG.info("destroyed  CustomContextListener");

    }
}
