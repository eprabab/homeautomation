FROM openjdk:8-alpine

RUN apk --no-cache add curl

ARG JAR_FILE=./target/homeautomation-1.0-SNAPSHOT.jar

COPY ${JAR_FILE} homeautomation-1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/homeautomation-1.0-SNAPSHOT.jar"]