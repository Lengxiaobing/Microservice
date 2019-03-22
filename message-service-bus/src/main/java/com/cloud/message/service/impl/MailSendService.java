package com.cloud.message.service.impl;

/**
 * @Description: 发送信息服务接口
 * @Author: ZX
 * @Date: 2019/2/14 17:59
 */
public interface MailSendService {

    /**
     * 发送手机验证码
     *
     * @param mobile
     * @param message
     * @return
     */
    boolean sendMobileMessage(String mobile, String message);

    /**
     * 发送邮箱验证码
     *
     * @param email
     * @param message
     * @return
     */
    boolean sendEmailMessage(String email, String message);
}
