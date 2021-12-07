#!/bin/bash

cd ../application
mvn clean spring-boot:build-image

cd ../backend
mvn clean spring-boot:build-image

kind load docker-image spring-native-counter-application:0.0.1-SNAPSHOT spring-native-counter-backend:0.0.1-SNAPSHOT

cd ../infrastructure
kubectl apply -f ./k8s