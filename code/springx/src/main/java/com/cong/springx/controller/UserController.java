package com.cong.springx.controller;

import com.cong.springx.model.User;
import com.cong.springx.service.UserService;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping(path = "/v1.0/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController  {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{city_id}/{user_id}")
    public User queryUser(@PathVariable(name = "city_id")String cityId,
                          @PathVariable(name = "user_id")String userId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        User user = new User();
        user.setUserCity(cityId);
        user.setUserId(Integer.valueOf(userId));
        user.setPhone("12345678");
        user.setPwd("pwd");
        user.setCreateTime(new Date());

        int i = userService.insertUser(user);
        String header = request.getHeader("X-Auth-Token");
        response.setHeader("id" ,"002");
        return user;
    }

}
