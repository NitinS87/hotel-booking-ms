FROM openjdk:17-jdk-alpine

WORKDIR /opt/discovery-service

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 9001

ENTRYPOINT ["java","-jar","app.jar"]