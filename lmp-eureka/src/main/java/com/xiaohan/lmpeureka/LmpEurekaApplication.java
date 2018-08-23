package com.xiaohan.lmpeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //注解作用：让应用变成Eureka服务器
public class LmpEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmpEurekaApplication.class, args);
    }
}
