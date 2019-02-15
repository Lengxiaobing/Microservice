package com.cloud.user.mapper;

import com.cloud.user.model.UserInfo;
import org.apache.ibatis.annotations.*;

/**
 * @Description: mybatis实现
 * @Author: ZX
 * @Date: 2019/2/14 18:02
 */
@Mapper
public interface UserMapper {

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Select("select id, username, password, real_name as realName," +
            "mobile, email from pe_user where id=#{id}")
    UserInfo getUserById(@Param("id") int id);

    /**
     * 根据名称查询用户
     *
     * @param username
     * @return
     */
    @Select("select id, username, password, real_name as realName," +
            "mobile, email from pe_user where username=#{username}")
    UserInfo getUserByName(@Param("username") String username);

    /**
     * 根据名称和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select id, username, password, real_name as realName," +
            "mobile, email from pe_user where username=#{username} and password=#{password}")
    UserInfo getUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 用户注册
     *
     * @param userInfo
     */
    @Insert("insert into pe_user (username, password, real_name, mobile, email)" +
            "values (#{u.username}, #{u.password}, #{u.realName}, #{u.mobile}, #{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);

    /**
     * 根据用户名删除用户
     *
     * @param username
     */
    @Delete("delete from pe_user where username=#{username}")
    void deleteUserByName(@Param("username") String username);


}
