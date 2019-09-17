package com.jackie.blogamqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogAmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
        Map<String,Object> map=new HashMap<>();
        map.put("msg","test");
        map.put("info","这是测试数据");
        map.put("context", Arrays.asList("adminuserjacs",
                new Integer(1233232),true,"this is test data"));
        //对象被默认序列化后发送数据
        rabbitTemplate.convertAndSend("adminjackie.direct","adminjackie.news",map);
    }


    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("adminjackie.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }
}
