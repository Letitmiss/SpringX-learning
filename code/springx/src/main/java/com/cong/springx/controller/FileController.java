package com.cong.springx.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@RestController
@RequestMapping(path = "v1.0")
public class FileController {

    @Autowired
    @Qualifier("infoLog")
    public Logger INFO_LOG;

   @RequestMapping(path = "/upload",method = RequestMethod.POST)
   public String upload(@RequestParam("head_img")MultipartFile file, HttpServletRequest request) throws FileNotFoundException {

       String name = request.getParameter("name");

       String originalFilename = file.getOriginalFilename();

        INFO_LOG.info("uploda File name = {}", originalFilename);


       String suffixName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
       INFO_LOG.info("uploda File name suffixName = {}", suffixName);

       String newFileName = UUID.randomUUID() + originalFilename;

       String path = ResourceUtils.getURL("classpath:").getPath();
       File dest = new File(path +  newFileName);

       try {
           file.transferTo(dest);
           return newFileName;
       } catch (IOException e) {
           e.printStackTrace();
           return "upload failed";
       }
   }

}
