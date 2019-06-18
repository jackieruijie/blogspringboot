package com.jackie.blogweb.model;

import java.util.Date;

/**
 * @program: springboot
 * @ClassName: Access
 * @description: 访问实体表
 * @author: jackie
 * @create: 2019-06-18 17:01
 * @VERSION:v1.0
 **/

public class Access {
    private String id;//id
    private String ipaddr;//IP地址
    private String addr;//ip所属地区
    private Date viewTime;//访问时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }
}
