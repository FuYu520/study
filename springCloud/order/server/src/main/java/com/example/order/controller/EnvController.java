package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : FuYu
 * @Description :
 */

@RestController
@RequestMapping("env")
@RefreshScope(proxyMode = ScopedProxyMode.NO)
public class EnvController {

    @Value("${env.ip}")
    private String env;

    @GetMapping("/print")
    private String print() {
        return env;
    }
}
