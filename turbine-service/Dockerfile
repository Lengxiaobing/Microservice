FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER zx 787681084@qq.com

COPY target/turbine-service-1.0-SNAPSHOT.jar /turbine-service.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/turbine-service.jar"]