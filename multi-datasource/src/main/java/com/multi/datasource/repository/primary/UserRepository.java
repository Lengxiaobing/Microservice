package com.multi.datasource.repository.primary;


import com.multi.datasource.entity.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author zx
 * @see User
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 获取是否存在该用户名的用户
     *
     * @param username 用户名
     * @return 是否存在该用户名
     */
    boolean existsByUsername(String username);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findByUsername(String username);
}