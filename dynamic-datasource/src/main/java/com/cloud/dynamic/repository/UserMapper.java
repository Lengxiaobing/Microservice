package com.cloud.dynamic.repository;


import com.cloud.dynamic.annotation.DataSource;
import com.cloud.dynamic.entity.User;

import java.util.List;

/**
 * @Description: UserMapper接口
 * @Author: ZX
 * @Date: 2019/3/16 16:58
 */
@DataSource("slave1")
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @DataSource
    int save(User user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @DataSource("slave1")
    int update(User user);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DataSource
    int deleteById(Long id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @DataSource("slave1")
    User selectById(Long id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> selectAll();
}
