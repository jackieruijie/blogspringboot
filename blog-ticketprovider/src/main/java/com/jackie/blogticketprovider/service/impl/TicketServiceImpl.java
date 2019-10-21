package com.jackie.blogticketprovider.service.impl;


import com.jackie.blogticketprovider.service.TicketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket(String name) {
        return name + "买了《拯救大兵瑞恩》";
    }
}
