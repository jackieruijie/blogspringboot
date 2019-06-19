package com.jackie.blogweb.service.impl;

import com.jackie.blogweb.mapper.AccessMapper;
import com.jackie.blogweb.mapper.CounterMapper;
import com.jackie.blogweb.model.Access;
import com.jackie.blogweb.service.IndexService;
import com.jackie.blogweb.util.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @program: springboot
 * @ClassName: IndexServiceImpl
 * @description: IndexServiceImpl
 * @author: jackie
 * @create: 2019-06-19 16:30
 * @VERSION:v1.0
 **/
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private AccessMapper accessMapper;

    @Resource
    private CounterMapper counterMapper;

    @Override
    public int addViewLog(HttpServletRequest request) {
        Access access=new Access();

        try {
            //获取访问的地区
            String add=AddressUtils.getAddresses(AddressUtils.getIpAddr(request),"utf-8");
            access.setId(UUID.randomUUID().toString().replaceAll("-",""));
            access.setAddr(add);
            access.setIpaddr(AddressUtils.getIpAddr(request));
            access.setViewTime(new Date());
            accessMapper.insertSelective(access);//添加访问地区信息
            counterMapper.updateWebViews();//更新网站访问次数
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
