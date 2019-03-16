package com.multi.datasource.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author zx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 3207255537958239823L;
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "snowId")
    @GenericGenerator(name = "snowId", strategy = "com.multi.datasource.generator.SnowflakeIdGenerator")
    private Long userId;
    /**
     * 用户名称
     */
    @Column(length = 60, unique = true, nullable = false)
    private String username;
    /**
     * 用户昵称
     */
    @Column(length = 60)
    private String nickname;
    /**
     * 用户密码
     */
    @Column(length = 60, nullable = false)
    private String password;
    /**
     * 用户电话
     */
    @Column(length = 30)
    private String mobile;
    /**
     * 用户邮箱
     */
    @Column(length = 60)
    private String email;
    /**
     * 用户性别
     */
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;
    /**
     * 用户状态
     */
    @Enumerated(EnumType.ORDINAL)
    private State state;
    /**
     * 用户备注
     */
    @Column(length = 120)
    private String notes;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;

    /**
     * 用户性别
     *
     * @author gwyon
     */
    public enum Sex {
        /**
         * 未知的
         */
        unknown,
        /**
         * 男性
         */
        man,
        /**
         * 女性
         */
        woman
    }

    /**
     * 用户性别
     *
     * @author gwyon
     */
    public enum State {
        /**
         * 正常的
         */
        normal,
        /**
         * 危险的
         */
        danger,
        /**
         * 冻结的
         */
        frozen,
        /**
         * 删除的
         */
        delete
    }
}
