package com.cong.springx.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.swing.plaf.PanelUI;

@Component
//@ConfigurationProperties //打开这个注解,使用@Value也可以注入进来
@PropertySource({"classpath:resource.properties"})
@ConfigurationProperties(prefix = "model")
public class ModelTest {

    //@Value("${model.name}")
    private String name;

    //@Value("${model.domain}")
    private String domain;

    public ModelTest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
