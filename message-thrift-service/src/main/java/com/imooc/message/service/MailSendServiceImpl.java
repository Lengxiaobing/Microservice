package com.imooc.message.service;

import com.imooc.thrift.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 发送信息服务接口实现
 * @author: ZX
 * @date: 2018/12/21 9:32
 */
@Slf4j
@Component
@RefreshScope
public class MailSendServiceImpl implements MessageService.Iface {


    @Resource
    private JavaMailSender mailSender;

    /**
     * 注入常量：发送人
     */
    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public boolean sendMobileMessage(String mobile, String message) throws TException {
        System.out.println(mobile + ":" + message);
        return true;
    }

    @Override
    public boolean sendEmailMessage(String email, String message) throws TException {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);
            mail.setTo(email);
            mail.setSubject("测试");
            mail.setText(message);
            mailSender.send(mail);
            log.info("Text邮件已经发送。");
            return true;
        } catch (Exception e) {
            log.error("发送Text邮件时发生异常={}", e);
        }
        return false;
    }
}
