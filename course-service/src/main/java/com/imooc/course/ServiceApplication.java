package com.imooc.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Michael on 2017/10/28.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
