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

    /**
     * 登陆页面跳转
     * @return
     */

    @RequestMapping("login")
    public String login() {
        log.info("执行login方法----------------------");
        return "/views/login";
    }

    /**
     * 注册页面跳转
     * @return
     */
    @RequestMapping("register")
    public String register() {
        log.info("执行register方法------------------");
        return "/views/register";
    }


    /**
     * 忘记密码
     * @return
     */
    @RequestMapping("forgetPass")
    public String forgetPass() {
        System.out.println("这是忘记密码的方法！！！");
        log.info("执行forgetPass方法------------------");
        return "/views/forgetpass";
    }





}
