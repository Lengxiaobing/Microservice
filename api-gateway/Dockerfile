FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER xxx xxx@cloud.com

COPY target/api-gateway-1.0-SNAPSHOT.jar /api-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
