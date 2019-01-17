FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER zx 787681084@qq.com

COPY target/eureka-registration-center-1.0-SNAPSHOT.jar /eureka-registration-center.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/eureka-registration-center.jar"]