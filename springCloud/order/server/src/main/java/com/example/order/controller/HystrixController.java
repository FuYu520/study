package com.example.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Author : FuYu
 * @Description :
 */
@RestController
/**
 * 默认降级提示
 */
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    /**
     * fallbackMethod = "fallback" 异常错误调用方法
     * HystrixCommandProperties 配置文件名称
     * execution.isolation.thread.timeoutInMilliseconds 超时设置
     * circuitBreaker 断路器
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //设置熔断
            //最小请求数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求
            //时间范围--时间窗口
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //错误百分比条件 10次调用 7次异常，断路器设置为打开状态，否则设置为关闭状态
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    //@HystrixCommand
    @GetMapping("/getProductInfoList")
    public String productInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8083/product/listForOrder",
                Arrays.asList("157875196366160022"), String.class);
        //抛出异常也会触发降级
        //throw new RuntimeException("发送异常了");
    }

    private String fallback() {
        return "请稍后再拨";
    }

    private String defaultFallback() {
        return "默认请稍后再拨";
    }
}
