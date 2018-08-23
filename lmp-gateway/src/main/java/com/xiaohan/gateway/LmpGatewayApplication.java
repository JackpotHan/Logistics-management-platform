package com.xiaohan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //注解作用：让服务使用eureka服务器
public class LmpGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmpGatewayApplication.class, args);
    }
}
