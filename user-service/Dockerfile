FROM 192.168.3.34:80/library/openjdk:8-jre-alpine
MAINTAINER zx 787681084@cloud.com

COPY target/user-service-1.0-SNAPSHOT.jar /user-service.jar

EXPOSE 8031

ENTRYPOINT ["java", "-jar", "/user-service.jar"]
