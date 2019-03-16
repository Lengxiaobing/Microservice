# Spring Cloud 微服务学习
**学习Spring Cloud架构，并Docker化部署。**  
**版本：**  
jdk：1.8.0  
spring-boot-starter-parent：2.1.2.RELEASE  
spring-cloud-dependencies：Greenwich.RC2  
## 模块组成
**api-gateway**：Gateway网关

**config-center**: 配置中心Git/SVN

**eureka-registration-center**: Eureka注册中心

**message-service**：测试程序-服务提供者

**turbine-service**： 熔断监控hystrix-dashboard，netflix-turbine

**user-service**：测试程序-服务调用者（包含熔断示例）
