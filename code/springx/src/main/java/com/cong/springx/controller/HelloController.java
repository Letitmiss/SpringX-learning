package com.cong.springx.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1.0")
public class HelloController  {

    @Autowired
    @Qualifier("debugLog")
    public  Logger DEBUG_LOG;

    @Autowired
    @Qualifier("infoLog")
    public  Logger INFO_LOG;

    @Autowired
    @Qualifier("systemLog")
    public  Logger SYSTEM_LOG;

    @Autowired
    @Qualifier("errorLog")
    public  Logger ERROR_LOG;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String name(@RequestParam(name = "name") String name) {

        DEBUG_LOG.info("info: " + name);

        return "springx hello ," + name;
    }

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi() {
        return "springx hello ,";
    }

}
