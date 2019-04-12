FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER zx 787681084@qq.com

COPY target/dynamic-datasource-1.0-SNAPSHOT.jar /dynamic-datasource.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/dynamic-datasource.jar"]