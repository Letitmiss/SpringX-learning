package com.cong.springx.web.filter;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter是Servlet技术中最实用的技术，Web开发人员通过Filter技术，对web服务器管理的所有web资源：
 * 例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，从而实现一些特殊的功能。
 * 例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。
 * 它主要用于对用户请求进行预处理，也可以对HttpServletResponse进行后处理。
 * 使用Filter的完整流程：Filter对用户请求进行预处理，接着将请求交给Servlet进行处理并生成响应，最后Filter再对服务器响应进行后处理。
 *
 * springboot第一种filter的实现 :
 * 1. 实现java,servlet.filter类
 * 2. 通过 @Bean FilterRegistrationBean注册filter
 * 3. 在注册的时候,可以指定Servlet, 不指定默认所有; 指定url
 */
public class CustomFilter implements Filter {

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    private static final String AUTH_TOKEN = "X-Auth-Token";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        INFO_LOG.info(" 001-init CustomFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        INFO_LOG.info("001- do custom filter before dofilter");
        HttpServletRequest request= (HttpServletRequest)servletRequest;
        String header = request.getHeader(AUTH_TOKEN);
        System.out.println( AUTH_TOKEN+ " : "+ header);
        filterChain.doFilter(servletRequest,servletResponse);

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        INFO_LOG.info("001- do custom filter aflter dofilter");

    }

    /**
     * 容器销毁的时候被调用
     */
    @Override
    public void destroy() {
        INFO_LOG.info("001 -  destroy  custom Filter");
    }
}
