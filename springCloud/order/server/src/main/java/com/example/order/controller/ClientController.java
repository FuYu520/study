package com.example.order.controller;


import com.example.product.client.ProductClient;
import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : FuYu
 * @Description :
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //第一种方式，(RestTemplate,url写死)不适合负载均衡的情况和不知道ip端口的情况
        //RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        //第二种(利用LoadBalancerClient通过应用名获取url，然后再使用RestTemplate)
//        ServiceInstance product = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", product.getHost(), product.getPort());
//        String response = restTemplate.getForObject(url + "/msg", String.class);

        //第三种
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("response={}",response);
//        return response;
        String response = productClient.msg();

        log.info("response={}",response);
        return response;
    }

//    @GetMapping("/getProductInfoList")
//    public String getProductInfoList() {
//        List<ProductInfoOutput> productInfos = productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
//        log.info("productInfos={}",productInfos);
//        return "ok";
//    }

    @GetMapping("/decreaseStock")
    public String decreaseStock() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("164103465734242707", 3)));
        return "ok";
    }
}
