package com.cloud.dynamic;

import com.cloud.dynamic.aop.DynamicDataSourceAnnotationAdvisor;
import com.cloud.dynamic.aop.DynamicDataSourceAnnotationInterceptor;
import com.cloud.dynamic.register.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: 启动类
 * @Author: ZX
 * @Date: 2019/3/16 17:00
 */
@Import(DynamicDataSourceRegister.class) // 注册动态多数据源
@MapperScan("com.cloud.dynamic.repository") // 对应的mapper类的路径
@SpringBootApplication
@EnableTransactionManagement // 开启事物管理
public class DynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }

    @Bean
    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
    }
}
