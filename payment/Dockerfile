FROM openjdk:17-jdk-alpine

WORKDIR /opt/payment-ms

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","app.jar"]