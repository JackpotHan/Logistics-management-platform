package com.xiaohan.lmpeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LmpEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmpEurekaApplication.class, args);
    }
}
