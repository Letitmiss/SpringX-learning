package com.cong.springx.web.listener;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义Listener(常用的监听器 servletContextListener、httpSessionListener、servletRequestListener)
 *
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletContext servletContext = servletRequestEvent.getServletContext();
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        INFO_LOG.info("destroyed  RequestListener");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        INFO_LOG.info("init RequestListener ");
    }
}
