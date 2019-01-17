#!/usr/bin/env bash
mvn clean package

docker build -t 192.168.3.34:80/micro-service/eureka-registration-center:latest .
docker push 192.168.3.34:80/micro-service/eureka-registration-center:latest