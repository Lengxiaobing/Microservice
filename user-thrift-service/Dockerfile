FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER zx 787681084@imooc.com

COPY target/user-thrift-service-1.0-SNAPSHOT.jar /user-service.jar

ENTRYPOINT ["java", "-jar", "/user-service.jar"]
