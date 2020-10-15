FROM openjdk:8-alpine

RUN apk add --update  git

RUN git clone https://github.com/xmails/homeautomation.git /homeautomation

ARG JAR_FILE=./target/homeautomation-1.0-SNAPSHOT.jar

COPY ${JAR_FILE} homeautomation-1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/homeautomation-1.0-SNAPSHOT.jar"]