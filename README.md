# Spring Cloud 微服务学习
**学习Spring Cloud架构，并Docker化部署。**  
**版本：**  
jdk：1.8.0  
spring-boot-starter-parent：2.1.2.RELEASE  
spring-cloud-dependencies：Greenwich.RC2  
## 模块组成
**api-gateway**：Gateway网关

**config-center**：配置中心Git/SVN

**eureka-registration-center**：Eureka注册中心

**message-service**：测试程序-服务提供者

**turbine-service**：熔断监控hystrix-dashboard，netflix-turbine

**user-service**：测试程序-服务调用者（包含熔断示例）

**multi-datasource**：多数据源配置，通过编写数据源的配置类实现（有多少个数据源就写多少个数据源的配置类）

**dynamic-datasource**：动态数据源切换，通过继承AbstractRoutingDataSource来动态选择DataSource路由，通过AOP的方式进行切换。

**config-center-bus**：消息总线bus版配置中心

**message-service-bus**：测试程序-服务提供者-消息总线bus版