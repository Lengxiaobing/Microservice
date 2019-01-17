#!/usr/bin/env bash

mvn package

docker build -t 192.168.3.34:80/micro-service/api-gateway-zuul:latest .

docker push 192.168.3.34:80/micro-service/api-gateway-zuul:latest