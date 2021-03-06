package com.cong.provider01.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @RequestMapping(value = "/provider/hello", method = RequestMethod.GET)
    public String hello() {
        return "<h1>hello,privder 1 gege<h1> ";
    }

    @RequestMapping(value = "/provider/hello", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "error")
    public String hello2() {
        return "<h1>hello,privder 1 gege<h1> ";
    }

    public String error() {
        return "error";
    }
}
