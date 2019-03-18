package com.cloud.dynamic.entity;


import lombok.Data;

/**
 * @Description: 用户
 * @Author: ZX
 * @Date: 2019/3/16 16:53
 */
@Data
public class User {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 1=男 2=女 其他=保密
     */
    private Integer sex;
}
