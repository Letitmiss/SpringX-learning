package com.cong.springx.controller;

import com.cong.springx.common.exception.ErrorCode;
import com.cong.springx.common.exception.ServerException;
import com.cong.springx.model.ModelTest;
import com.cong.springx.model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1.0")
public class ExceptionController {

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    @GetMapping(path = "/exception")
    public User value (@RequestParam(name = "name") String  name) {
        try {
            if (StringUtils.isEmpty(name)) {
                throw new ServerException(ErrorCode.PARAM_INVALID,"name is null");
            }
            return new User("congcong","shenzhen","138XXXX");
        } catch (ServerException ex) {
            INFO_LOG.error("server exception", ex);
            throw new ServerException(ex.getCode(),ex.getMsg(), HttpStatus.FORBIDDEN.value());
        }

    }

    @GetMapping(path = "/exception/test")
    public User value () {

       throw new ServerException("500","Test Exception web",HttpStatus.FORBIDDEN.value());

    }


}
