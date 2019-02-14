FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER xxx xxx@cloud.com

COPY target/api-gateway-zuul-1.0-SNAPSHOT.jar /api-gateway-zuul.jar

ENTRYPOINT ["java", "-jar", "/api-gateway-zuul.jar"]
