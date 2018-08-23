package com.xiaohan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient //注解作用：让服务使用eureka服务器
@EnableZuulProxy
@EnableFeignClients
@EnableHystrix
public class LmpGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmpGatewayApplication.class, args);
    }
}
