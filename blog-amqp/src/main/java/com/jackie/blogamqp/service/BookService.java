package com.jackie.blogamqp.service;

import com.jackie.blogamqp.model.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {


  /*  @RabbitListener(queues = {"adminjackie.news","adminuser.news"})
    public void getMessage(Book book){
        System.out.println("RabbitListener --------> 我已经收到消息："+book.toString());
    }*/


    @RabbitListener(queues = "adminjackie.emps")
    public void getMessage(Message message) {
        System.out.println("toString----------->" + message.toString());
        System.out.println("body----------->" + message.getBody());
        System.out.println("properties--------->" + message.getMessageProperties());
    }


}
