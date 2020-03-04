package com.example.order.meaasge;

import com.example.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Author : FuYu
 * @Description :
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {


//    @StreamListener(value = StreamClient.INPUT)
//    public void process(Object message){
//        log.info("StreamReceiver:{}", message);
//    }

    /**
     * 接收orderDto对象
     * @param message
     */
    @StreamListener(value = StreamClient.INPUT)
    @SendTo(value = StreamClient.INPUT2)
    public String process(OrderDTO message){
        log.info("StreamReceiver:{}", message);
        //发送mq消息,告知收到了信息
        return "received ";
    }

    @StreamListener(value = StreamClient.INPUT2)
    public void process2(String message){
        log.info("StreamReceiver2:{}", message);
        //发送mq消息
    }
}
