package com.cloud.user.service;

import com.cloud.user.service.impl.MessageService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 调用信息服务方法--熔断回调实现
 * @Author: ZX
 * @Date: 2019/2/18 15:04
 */
@Component
@Slf4j
public class MessageServiceImpl implements FallbackFactory<MessageService> {

    @Override
    public MessageService create(Throwable cause) {
        log.error("", cause);

        return new MessageService() {
            @Override
            public boolean mobileMessage(String mobile, String message) {
                return false;
            }

            @Override
            public boolean mailMessage(String email, String message) {
                return false;
            }

            @Override
            public String test() {
                log.error("触发熔断");
                return null;
            }
        };
    }
}
