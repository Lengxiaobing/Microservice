package com.cloud.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户信息
 * @Author: ZX
 * @Date: 2019/2/14 17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    private Integer id;

    private String username;

    private String password;

    private String realName;

    private String mobile;

    private String email;

}
