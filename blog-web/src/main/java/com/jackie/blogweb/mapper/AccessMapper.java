package com.jackie.blogweb.mapper;

import com.jackie.blogweb.model.Access;

/**
 * @program: springboot
 * @ClassName: AccessMapper
 * @description: 访问接口
 * @author: jackie
 * @create: 2019-06-18 17:03
 * @VERSION:v1.0
 **/
public interface AccessMapper {
    public int insertSelective(Access access);

}
