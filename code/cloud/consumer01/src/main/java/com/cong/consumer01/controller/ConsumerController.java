package com.cong.consumer01.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://springcloud-provider1/provider/hello", String.class);
        return forEntity.getBody();
    }

    @RequestMapping(value = "/consumer/hi", method = RequestMethod.GET)

   // @HystrixCommand(fallbackMethod = "error",ignoreExceptions = Exception.class) //异常交给用户处理
    @HystrixCommand(fallbackMethod = "error")
    public String hi () {

        int a= 10 / 0 ;

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://springcloud-provider1/provider/hello2", String.class);
        return forEntity.getBody();
    }

    public String error(Throwable throwable) {

        System.out.println(throwable.getMessage());
        return "error";
    }

}
