package com.jackie.blogasd.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.ExecutionException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @program: springboot
 * @ClassName: LoginController
 * @description: 控制登陆
 * @author: jackie
 * @create: 2019-06-18 15:47
 * @VERSION:v1.0
 **/
@RestController
@RequestMapping("asd")
public class LoginController {


    @RequestMapping("login")
    public String userLogin(String username,String password){
        //获取当前登陆用户
        Subject subject= SecurityUtils.getSubject();
        //如果当前用户未登录则进行登陆操作
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);//记住我
            subject.login(token);
            return "ok";
        }catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误！");
            return "redirect:/userlogin";
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "login";

        }







}
