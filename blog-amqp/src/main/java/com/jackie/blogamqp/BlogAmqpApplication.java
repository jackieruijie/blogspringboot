package com.jackie.blogamqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class BlogAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAmqpApplication.class, args);
    }

}
