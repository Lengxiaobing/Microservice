package com.multi.datasource.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志信息
 *
 * @author zx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log_info")
public class LogInfo implements Serializable {
    private static final long serialVersionUID = -2406211969474286490L;
    /**
     * 日志id
     */
    @Id
    @GeneratedValue(generator = "snowId")
    @GenericGenerator(name = "snowId", strategy = "com.multi.datasource.generator.SnowflakeIdGenerator")
    private Long logId;
    /**
     * 远程地址
     */
    @Column(length = 30)
    private String remote;
    /**
     * 请求url
     */
    @Column(length = 100)
    private String request;
    /**
     * 请求方法
     */
    @Column(length = 10)
    private String method;
    /**
     * 调用方法
     */
    @Column(length = 200)
    private String point;
    /**
     * 备注信息
     */
    @Column(length = 100)
    private String notes;
    /**
     * 请求时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date times;
    /**
     * 简略错误信息
     */
    @Column(length = 300)
    private String error;
}
