package com.cloud.user.service;

import com.cloud.user.mapper.UserMapper;
import com.cloud.user.model.UserInfo;
import com.cloud.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 查询用户实现
 * @Author: ZX
 * @Date: 2019/2/14 17:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
    @Override
    public UserInfo  getUserByNameAndPassword(String username,String password) {
        return userMapper.getUserByNameAndPassword(username,password);
    }


    @Override
    public void registerUser(UserInfo userInfo) {
        userMapper.registerUser(userInfo);
    }

    @Override
    public void deleteUserByName(String username) {
        userMapper.deleteUserByName(username);
    }


}
