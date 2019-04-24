package com.cloud.eureka.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用于k8s 心跳健康检查
 * @Author: ZX
 * @Date: 2019/4/24 10:58
 */
@RestController
public class HealthController {
    @GetMapping(path = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
    public String health() {
        return "ok";
    }
}