package com.cloud.dynamic.service;

import com.cloud.dynamic.entity.User;
import com.cloud.dynamic.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户服务
 * @Author: ZX
 * @Date: 2019/3/16 17:00
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void testTransactional() {
        User user = new User();
        user.setUsername("Transactional");
        user.setPassword("Transactional");
        user.setSex(1);
        user.setAge(18);
        userMapper.save(user);
        User user1 = new User();
        user1.setId(2L);
        user1.setPassword("Transactional");
        // 返回插入的记录数 ，期望是1条 如果实际不是一条则抛出异常
        System.out.println(userMapper.update(user1));
        throw new RuntimeException();
    }


}
