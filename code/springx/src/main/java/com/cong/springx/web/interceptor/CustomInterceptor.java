package com.cong.springx.web.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：Interceptor 在AOP（Aspect-Oriented Programming）中用于在某个方法或字段被访问之前，
 * 进行拦截然后在之前或之后加入某些操作。比如日志，安全等。
 * 一般拦截器方法都是通过动态代理的方式实现。
 * 可以通过它来进行权限验证，或者判断用户是否登陆，或者是像12306 判断当前时间是否是购票时间。
 *
 * 1 .filter处理 -
 * 2. 拦截器的preHandle --
 * 3. 真正处理HandlerAdapter ---
 * 3. 拦截器 PostHandle --
 * 4. View渲染 ---
 * 5. afterCompletion  -
 * 6. --filter --
 *
 */
public class CustomInterceptor extends HandlerInterceptorAdapter {


    //请求处理之前进行调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("001---CustomInterceptor   preHandle ");
        String header = request.getHeader("X-Auth-Token");
        if (!StringUtils.isEmpty(header)) {
            System.out.println("X-Auth-Token:" + header);
            return true;  //表示调用下一个拦截器
        } else {
            System.out.println("header X-Auth-Token is null");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("0001-------CustomInterceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("001------CustomInterceptor afterCompletion");
    }
}
