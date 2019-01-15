FROM hub.mooc.com:8080/micro-service/openjdk:7-jre
MAINTAINER xxx xxx@imooc.com

COPY target/message-thrift-service-1.0-SNAPSHOT.jar /message-thrift-service.jar

ENTRYPOINT ["java", "-jar", "/message-thrift-service.jar"]