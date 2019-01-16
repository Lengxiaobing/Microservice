FROM 192.168.3.34:5000/openjdk:8-jre-alpine
MAINTAINER zx 787681084@qq.com

COPY target/eureka-registration-center-1.0-SNAPSHOT.jar /eureka-registration-center.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/eureka-registration-center.jar"]