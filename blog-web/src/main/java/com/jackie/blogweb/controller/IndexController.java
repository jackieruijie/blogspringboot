package com.jackie.blogweb.controller;

import com.jackie.blogweb.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    private final static Logger log= LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private IndexService indexService;

    //首页跳转
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        log.info("执行index方法");
        log.info(" comming  success!!!!!!");
        int res=indexService.addViews(request);
        if (res>0){
            return "/index";
        }
        return null;
    }

    //登陆
    @RequestMapping("login")
    public String login() {
        return "/views/login";
    }

    //注册
    @RequestMapping("register")
    public String register() {
        return "/views/register";
    }


}
