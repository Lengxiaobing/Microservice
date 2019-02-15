package com.cloud.user.controller;

import com.cloud.user.model.UserInfo;
import com.cloud.user.param.UserParam;
import com.cloud.user.service.impl.UserService;
import com.cloud.user.vo.BaseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户登录注册
 * @Author: ZX
 * @Date: 2019/1/22 11:47
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object login(@RequestBody UserParam param) {
        if (StringUtils.isBlank(param.getPassword())) {
            return new BaseVO(500, "密码不能为空");
        }

        UserInfo user = userService.getUserByNameAndPassword(param.getUsername(), param.getPassword());
        if (user == null) {
            return new BaseVO(500, "用户名或密码错误");
        }

        return new BaseVO("登录成功");
    }

    /**
     * 添加用户
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody UserParam param) {
        UserInfo userByName = userService.getUserByName(param.getUsername());
        if (userByName != null) {
            return new BaseVO(500, "该用户已存在");
        }

        UserInfo userInfo = UserInfo.builder()
                .username(param.getUsername())
                .password(param.getPassword())
                .realName(param.getRealName())
                .mobile(param.getMobile())
                .email(param.getEmail())
                .build();
        userService.registerUser(userInfo);
        return new BaseVO(true);
    }

    /**
     * 删除用户
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@RequestBody UserParam param) {
        userService.deleteUserByName(param.getUsername());
        return new BaseVO(true);
    }

}
