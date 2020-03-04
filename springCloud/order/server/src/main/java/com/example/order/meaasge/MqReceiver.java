package com.example.order.meaasge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author : FuYu
 * @Description :
 */
@Slf4j
@Component
public class MqReceiver {

    //1,@RabbitListener(queues = "myQueue")
    //2,自动创建队列@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3,自动创建Echange 和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value =  @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message){
        log.info("MqReceiver:{}", message);
    }


    /**
     * 数码供应商服务，接受消息
     */
    @RabbitListener(bindings = @QueueBinding(
            key = "computer",
            value =  @Queue("computerOrder"),
            exchange = @Exchange("myOrder")
    ))
    public void processComputer(String message){
        log.info("MqReceiverComputer:{}", message);
    }

    /**
     * 水果供应商服务，接受消息
     */
    @RabbitListener(bindings = @QueueBinding(
            key = "fruit",
            value =  @Queue("fruitOrder"),
            exchange = @Exchange("myOrder")
    ))
    public void processFruit(String message){
        log.info("MqReceiverFruit:{}", message);
    }
}
