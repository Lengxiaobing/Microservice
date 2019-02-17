package com.cloud.user.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * -@description: 调用信息服务方法
 * <p>
 * -@author: zhuxiang
 * -@create: 2019-02-17 14:26
 */
@FeignClient("message-service")
public interface MessageService {

    /**
     * 信息服务：发送手机验证码接口
     *
     * @param mobile
     * @param message
     * @return
     */
    @RequestMapping(value = "/message/mobile", method = RequestMethod.POST)
    boolean mobileMessage(@RequestParam("mobile") String mobile, @RequestParam("message") String message);

    /**
     * 信息服务：发送邮箱验证码接口
     *
     * @param email
     * @param message
     * @return
     */
    @RequestMapping(value = "/message/mail", method = RequestMethod.POST)
    boolean mailMessage(@RequestParam("email") String email, @RequestParam("message") String message);
}
