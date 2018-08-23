package com.xiaohan.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //注解作用：让服务使用eureka服务器
public class LmpSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmpSystemApplication.class, args);
    }
}
