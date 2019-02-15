package com.cloud.user.service.impl;

import com.cloud.user.model.UserInfo;

/**
 * @Description: 查询用户接口
 * @Author: ZX
 * @Date: 2019/2/14 17:47
 */
public interface UserService {

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    UserInfo getUserById(int id);

    /**
     * 根据名字查询用户
     *
     * @param username
     * @return
     */
    UserInfo getUserByName(String username);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    UserInfo getUserByNameAndPassword(String username, String password);

    /**
     * 注册
     *
     * @param userInfo
     */
    void registerUser(UserInfo userInfo);

    /**
     * 根据用户名删除用户
     *
     * @param username
     */
    void deleteUserByName(String username);
}
