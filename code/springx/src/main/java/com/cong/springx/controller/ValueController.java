package com.cong.springx.controller;

import com.cong.springx.model.ModelTest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource({"classpath:resource.properties"})
@RequestMapping(path = "/v1.0")
public class ValueController {

    @Value("${file.path}")
    private String filePath;

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

    @Autowired
    public ModelTest modelTest;

    @GetMapping(path = "/value")
    public void value () {

        INFO_LOG.info("@Value,receive value = {}",filePath);

        INFO_LOG.info("@Value,model test name = {}",modelTest.getName());

        INFO_LOG.info("@Value,model test domain = {}",modelTest.getDomain());

    }
}
