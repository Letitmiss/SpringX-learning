package com.cong.consumer01.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
    /**
     * 等价配置一个bean ：  id = 方法名 class = 返回值
     * @return
     */
    @LoadBalanced  //ribbon 负载均衡;默认规则
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule iRule () {
        return  new RandomRule();
    }




}
