package com.cong.springx.controller;

import com.cong.springx.model.ModelTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/v1.0")
public class FreemarkerController {

    @Autowired
    private ModelTest modelTest;

    @GetMapping(path = "/fm/user")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("model",modelTest);
        return "fm/user/user";  //不用加后缀文件已经写明后缀,前面也不用加 /
    }
}
