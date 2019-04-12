FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER zx 787681084@qq.com

COPY target/multi-datasource-1.0-SNAPSHOT.jar /multi-datasource.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/multi-datasource.jar"]