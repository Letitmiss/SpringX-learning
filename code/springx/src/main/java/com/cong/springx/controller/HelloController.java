package com.cong.springx.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
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


    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public String name(@RequestParam(name = "name") String name) {
        DEBUG_LOG.debug("debug :" + name);
        DEBUG_LOG.info("info: " + name);
        DEBUG_LOG.error("error :" + name);
        return "springx hello ," + name;
    }

}
