package com.cloud.message.controller;


import com.cloud.message.service.MailSendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 信息服务接口
 * @author: ZX
 * @date: 2019/1/17 9:06
 */
@RestController
@RequestMapping("/message")
public class MailSendController {

    @Autowired
    private MailSendServiceImpl mailSendService;

    /**
     * 发送手机验证码
     *
     * @param mobile
     * @param message
     * @return
     */
    @RequestMapping(value = "/mobile", method = RequestMethod.POST)
    @ResponseBody
    public boolean mobileMessage(@RequestParam String mobile, @RequestParam String message) {
        return mailSendService.sendMobileMessage(mobile, message);
    }

    /**
     * 发送邮箱验证码
     *
     * @param email
     * @param message
     * @return
     */
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    @ResponseBody
    public boolean mailMessage(@RequestParam String email, @RequestParam String message) {
        return mailSendService.sendEmailMessage(email, message);
    }

    /**
     * 熔断测试
     *
     * @return
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("空对象");
    }
}
