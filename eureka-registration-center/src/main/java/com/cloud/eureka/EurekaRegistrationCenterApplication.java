package com.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: Eureka注册中心
 * 
 * @author:   ZX
 * @date:     2019/1/16 15:16
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaRegistrationCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegistrationCenterApplication.class, args);
    }

}

