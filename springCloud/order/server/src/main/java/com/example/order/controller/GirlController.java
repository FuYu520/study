package com.example.order.controller;

import com.example.order.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : FuYu
 * @Description :
 */
@RestController
public class GirlController {

    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("girl/print")
    private String print() {
        return girlConfig.getName() + ":" + girlConfig.getAge();
    }
}
