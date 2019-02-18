package com.cloud.user.service;

import com.cloud.user.service.impl.MessageService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 调用信息服务方法--熔断回调实现
 * @Author: ZX
 * @Date: 2019/2/18 15:04
 */
@Component
public class MessageServiceImpl implements MessageService {


    /**
     * 信息服务：发送手机验证码接口，熔断回调方法
     *
     * @param mobile
     * @param message
     * @return
     */
    @Override
    public boolean mobileMessage(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "message") String message) {
        return false;
    }

    /**
     * 信息服务：发送邮箱验证码接口，熔断回调方法
     *
     * @param email
     * @param message
     * @return
     */
    @Override
    public boolean mailMessage(@RequestParam(value = "email") String email, @RequestParam(value = "message") String message) {
        return false;
    }
}
