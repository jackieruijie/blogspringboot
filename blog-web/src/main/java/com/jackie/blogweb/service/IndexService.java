package com.jackie.blogweb.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot
 * @ClassName: IndexService
 * @description: IndexService
 * @author: jackie
 * @create: 2019-06-19 16:29
 * @VERSION:v1.0
 **/
public interface IndexService {
    public int addViewLog(HttpServletRequest request);

}
