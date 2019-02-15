FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER zx 787681084@qq.com

COPY target/message-service-1.0-SNAPSHOT.jar /message-service.jar

ENTRYPOINT ["java", "-jar", "/message-service.jar"]