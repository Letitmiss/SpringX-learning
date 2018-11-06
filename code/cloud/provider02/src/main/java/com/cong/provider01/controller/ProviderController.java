package com.cong.provider01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @RequestMapping(value = "/provider/hello", method = RequestMethod.GET)
    public String hello() {
        return "<h1>hello,privder 2 gege<h1> ";
    }
}
