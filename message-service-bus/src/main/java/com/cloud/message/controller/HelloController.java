package com.cloud.message.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * -@description: 测试配置中心
 * <p>
 * -@author: xiang
 * -@create: 2019-03-22 19:46
 */
@RestController
@RefreshScope
public class HelloController {

    @Value("${parameter.hello}")
    private String hello;

    @RequestMapping("/hello")
    public String from() {
        return this.hello;
    }
}
