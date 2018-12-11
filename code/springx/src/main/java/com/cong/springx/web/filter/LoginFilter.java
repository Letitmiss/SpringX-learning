package com.cong.springx.web.filter;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * filter第二种开发模式
 * 1. 启动类,  @ServletComponentScan
 * 2. 实现filter接口, @WebFilter(filterName = "loginFilter",urlPatterns ="/v1.0/*" )
 */
@WebFilter(filterName = "loginFilter",urlPatterns ="/v1.0/*")
public class LoginFilter implements Filter {

    private static final String AUTH_TOKEN = "X-Auth-Token";

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        INFO_LOG.info("002--init login Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        INFO_LOG.info("002- do login filter  , dofilter before ");
        HttpServletRequest request= (HttpServletRequest)servletRequest;
        String header = request.getHeader(AUTH_TOKEN);
        System.out.println( AUTH_TOKEN+ " : "+ header);
        filterChain.doFilter(servletRequest,servletResponse);
        INFO_LOG.info("002- do login filter  , dofilter after ");
    }

    @Override
    public void destroy() {
        INFO_LOG.info("002 - destroy login  Filter");
    }
}
