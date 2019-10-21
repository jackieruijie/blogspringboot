package com.jackie.blogticketprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用过程：
 *  1.引入dubbo和zkclient相关依赖
 *  2.配置dubbo的扫描包和注册中心地址
 *  3.使用@Service发布服务
 */
@SpringBootApplication
public class BlogTicketproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogTicketproviderApplication.class, args);
    }

}
