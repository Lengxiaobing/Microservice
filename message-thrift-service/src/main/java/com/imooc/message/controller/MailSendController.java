package com.imooc.message.controller;


import com.imooc.message.service.MailSendServiceImpl;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 信息服务接口
 * @author: ZX
 * @date: 2019/1/17 9:06
 */
@RestController
@RequestMapping("message")
public class MailSendController {

    @Autowired
    private MailSendServiceImpl mailSendService;

    /**
     * 发送手机验证码
     *
     * @param mobile
     * @param message
     * @return
     * @throws TException
     */
    @RequestMapping(value = "mobile", method = RequestMethod.POST)
    @ResponseBody
    public boolean mobileMessage(@RequestParam String mobile, @RequestParam String message) throws TException {
        return mailSendService.sendMobileMessage(mobile, message);
    }

    /**
     * 发送邮箱验证码
     *
     * @param email
     * @param message
     * @return
     * @throws TException
     */
    @RequestMapping(value = "mail", method = RequestMethod.POST)
    @ResponseBody
    public boolean mailMessage(@RequestParam String email, @RequestParam String message) throws TException {
        return mailSendService.sendEmailMessage(email, message);
    }
}
