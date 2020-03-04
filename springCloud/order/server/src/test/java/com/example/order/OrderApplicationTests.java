package com.example.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author : FuYu
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderApplicationTests {

    /**
     * ribbitmq 实现了这个接口
     * todo ribbitmq 高级
     * 消息优先级
     * 消息延时处理
     * 消息qps设置
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
          amqpTemplate.convertAndSend("myQueue","now"+new Date());
    }

    @Test
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder","computer", "now"+new Date());
    }

}
