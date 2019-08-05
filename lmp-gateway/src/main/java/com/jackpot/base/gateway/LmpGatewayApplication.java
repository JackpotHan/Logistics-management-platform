package com.jackpot.base.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient //注解作用：让服务使用eureka服务器
@EnableHystrix
public class LmpGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmpGatewayApplication.class, args);
    }
}
