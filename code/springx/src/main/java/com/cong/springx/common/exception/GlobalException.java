package com.cong.springx.common.exception;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Configuration
@Component
@RestControllerAdvice  //统一异常处理
public class GlobalException {

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    /**
     * 拦截自定义异常并且作为返回值处理,同时可以设置http响应码
     * @param ex
     * @param response
     * @return
     */
    @ExceptionHandler(ServerException.class)
    public ErrorRsp handleServerExceptionHandle(ServerException ex, HttpServletResponse response) {

        ErrorRsp errorRsp = new ErrorRsp(ex.getCode(),ex.getMsg());
        if (ex.getStatus() != 0) {
            response.setStatus(ex.getStatus());
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
        return errorRsp;
    }

    /**
     * 拦截: 空指针异常,抛出自定义异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorRsp handleNullPointerException(NullPointerException ex)
    {
        ErrorRsp errorRsp = new ErrorRsp();
        INFO_LOG.error("ErrorCode:500, ErrorMessage:Internal System Error, NullPointerException occur.\n",ex);
        errorRsp.setErrorCode(ErrorCode.SERVER);
        errorRsp.setErrorMsg("Internal System Error, NullPointerException");
        return errorRsp;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorRsp handleException(Exception ex)
    {
        ErrorRsp errorRsp = new ErrorRsp();
        INFO_LOG.error("ErrorCode:500, ErrorMessage:Internal System Error,Exception occur.\n",ex);
        errorRsp.setErrorCode(ErrorCode.SERVER);
        errorRsp.setErrorMsg("Internal System Error, Exception ");
        return errorRsp;
    }

    /**
     * error页面跳转
     */
    /*@ExceptionHandler(ServerException.class)
    public Object handleServerExceptionHandle(ServerException ex, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error.html");
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }*/
}
