package com.jackie.blogweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //首页跳转
    @RequestMapping("/")
    public String index() {
        return "/index";
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
