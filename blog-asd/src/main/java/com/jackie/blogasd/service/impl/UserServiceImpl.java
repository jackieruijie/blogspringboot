package com.jackie.blogasd.service.impl;

import com.alibaba.fastjson.JSON;
import com.jackie.blogasd.mapper.TuserMapper;
import com.jackie.blogasd.model.Tuser;
import com.jackie.blogasd.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private TuserMapper userMapper;

    @Override
    public String insertUser(String userObject) {
        Map<String, String> userMap = (Map<String, String>) JSON.parse(userObject);
        if (userMap.containsKey("username")) {
            Tuser exitUser = userMapper.getUserByUsername(userMap.get("username").trim());
            if (exitUser != null) {
                log.info("用户已存在！");
                return "用户已存在！";
            }
        }
        Tuser user = new Tuser();
        user.setToId("asdfasd");
        user.setTuId(UUID.randomUUID().toString().replace("-", ""));//生成userId
        user.setSalt(UUID.randomUUID().toString().replace("-", ""));//生成盐值
        if (userMap.containsKey("username")) user.setLoginName(userMap.get("username").trim());
        if (userMap.containsKey("password")) user.setPassword(userMap.get("password").trim());
        if (userMap.containsKey("realname")) user.setRealName(userMap.get("realname").trim());
        if (userMap.containsKey("mobile")) user.setMobile(userMap.get("mobile").trim());
        if (userMap.containsKey("email")) user.setEmail(userMap.get("email").trim());
        user.setCreateTime(new Date());
        user.setIsApplied(0);//是否已激活
        user.setIsDelete(0);//是否已禁用
        user.setIsProfile(0);//是否已完善个人信息
        //密码加密 ；参数列表(加密方式，密码原值，盐值，加密次数)
        Object result = new SimpleHash("MD5", user.getPassword(), user.getSalt(), 1024);
        user.setPassword(result.toString());//加密后的密码
        Integer res = userMapper.insertSelective(user);
        return res.toString();
    }

    @Override
    public String userLogin(String uname, String password) {
        Tuser u = userMapper.getUserByUsername(uname);
        if (u == null) {
            return "用户不存在，请先注册";
        }
        Object result = new SimpleHash("MD5", password, u.getSalt(), 1024);//密码加密用于比对
        if (result.toString().equals(u.getPassword())) {
            return "登陆成功";
        } else {
            return "密码不正确，请验证密码后重新登陆";
        }
    }
}
