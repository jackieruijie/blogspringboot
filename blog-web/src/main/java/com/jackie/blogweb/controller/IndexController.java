package com.jackie.blogweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    //首页跳转
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        System.out.println("success!!!!!!");
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
