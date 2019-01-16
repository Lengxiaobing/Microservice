FROM 192.168.3.34:5000/openjdk:8-jre-alpine
MAINTAINER zx 787681084@qq.com

COPY target/message-thrift-service-1.0-SNAPSHOT.jar /message-thrift-service.jar

ENTRYPOINT ["java", "-jar", "/message-thrift-service.jar"]