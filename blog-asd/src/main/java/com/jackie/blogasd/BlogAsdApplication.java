package com.jackie.blogasd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
//@SpringBootApplication
public class BlogAsdApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAsdApplication.class, args);
    }



    private CorsConfiguration buildConfig() {
        List<String> exposedHeaders = new ArrayList<>();
        exposedHeaders.add("Access-Control-Allow-Origin");
        exposedHeaders.add("Access-Control-Allow-Credentials");

        List<String> allowedMethods = new ArrayList<>();
        allowedMethods.add("GET");
        allowedMethods.add("POST");

        List<String> allowedHeaders = new ArrayList<String>();
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("X-Requested-With");
        allowedHeaders.add("accept");
        allowedHeaders.add("Origin");
        allowedHeaders.add("Access-Control-Request-Method");
        allowedHeaders.add("Access-Control-Request-Headers");
        allowedHeaders.add("Accept");//这里设置要和前端js名字一样
        allowedHeaders.add("userToken");//这里设置要和前端js名字一样

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1.允许任何域名使用
        corsConfiguration.setAllowedHeaders(allowedHeaders);
        corsConfiguration.setAllowedMethods(allowedMethods);
        corsConfiguration.setExposedHeaders(exposedHeaders);
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

}
