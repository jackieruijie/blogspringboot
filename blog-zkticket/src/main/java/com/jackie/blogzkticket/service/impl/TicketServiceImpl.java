package com.jackie.blogzkticket.service.impl;

import com.jackie.blogticketprovider.service.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl {
    @Reference
    TicketService ticketService;

    public void getTicket(){
        String user = ticketService.getTicket("张三");
        System.out.println("info:"+user);
    }
}
