package com.example.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : FuYu
 * @Description :
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "123 2";
    }
}
