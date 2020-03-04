package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.meaasge.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author : FuYu
 * @Description :
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    //    @GetMapping("/sendMessage")
//    public void process(){
//        streamClient.output().send(MessageBuilder.withPayload("now " + new Date()).build());
//    }

    /**
     * 发送ordetdto对象
     */
    @GetMapping("/sendMessage")
    public void process() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1");
        orderDTO.setBuyerAddress("address");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
