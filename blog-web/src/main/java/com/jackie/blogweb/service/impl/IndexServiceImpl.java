package com.jackie.blogweb.service.impl;

import com.jackie.blogweb.controller.IndexController;
import com.jackie.blogweb.mapper.AccessMapper;
import com.jackie.blogweb.mapper.CounterMapper;
import com.jackie.blogweb.model.Access;
import com.jackie.blogweb.model.Counter;
import com.jackie.blogweb.service.IndexService;
import com.jackie.blogweb.util.AddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Service
public class IndexServiceImpl implements IndexService {
    private Logger log= LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AccessMapper accessMapper;

    @Autowired
    private CounterMapper counterMapper;

    @Override
    public int addViews(HttpServletRequest request) {
        int res=0;
        try {
            Access access=new Access();
            String ip= AddressUtils.getIPAddress(request);
            access.setId(UUID.randomUUID().toString().replaceAll("-",""));//get ID
            access.setIpaddr(ip);
            access.setAddr(AddressUtils.getAddresses(ip,"utf-8"));
//            access.setAddr(null);
            access.setViewTime(new Date());
            res=accessMapper.insertSelective(access);
            counterMapper.updateWebViews();
            if (res>0){
                System.out.println("add success");
                System.out.println(access.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
