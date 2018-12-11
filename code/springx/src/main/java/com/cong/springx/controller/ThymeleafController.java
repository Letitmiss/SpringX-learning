package com.cong.springx.controller;

import com.cong.springx.model.ModelTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/v1.0")
public class ThymeleafController {

    @Autowired
    private ModelTest modelTest;

    @GetMapping(path = "/thymeleaf/test")
    public String thyme() {
        return "thyme"; //不用后缀, 后缀已经被指明
    }

    @GetMapping(path = "/thymeleaf/info")
    public String info(ModelMap modelMap) {
        modelMap.addAttribute("model",modelTest);
        return "info"; //不用后缀, 后缀已经被指明
    }
}

