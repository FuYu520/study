package com.example.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author : FuYu
 * @Description : spring Cloud Bus 刷新配置在类上
 */
@Component
@Data
@RefreshScope
@ConfigurationProperties("girl")
public class GirlConfig {
    private String name;

    private int age;
}
