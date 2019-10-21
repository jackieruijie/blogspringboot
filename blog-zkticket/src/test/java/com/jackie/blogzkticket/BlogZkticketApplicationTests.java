package com.jackie.blogzkticket;

import com.jackie.blogzkticket.service.impl.TicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogZkticketApplicationTests {
    @Autowired
    TicketServiceImpl ticketService;

    @Test
    public void contextLoads() {
        ticketService.getTicket();
    }

}
