FROM openjdk:8-alpine

ARG JAR_FILE=./target/homeautomation-1.0-SNAPSHOT.jar

COPY ${JAR_FILE} homeautomation-1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","homeautomation-1.0-SNAPSHOT.jar"]