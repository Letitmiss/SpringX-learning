package com.cong.consumer01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/hello", method = RequestMethod.GET)
    public String hello () {

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/provider/hello", String.class);
        return forEntity.getBody();
    }
}
