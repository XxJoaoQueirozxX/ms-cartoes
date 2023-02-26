FROM maven:3.8.5-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG RABBIT_MQ_SERVER=rabbitmq_host
ARG RABBIT_MQ_PORT=5672
ARG RABBIT_MQ_USER=rabbitmq_user
ARG RABBIT_MQ_PASSWORD=rabbitmq_password
ARG EUREKA_SERVER=localhost
ARG EUREKA_PORT=8761
ARG EUREKA_USER=eureka_user
ARG EUREKA_PASSWORD=eureka_password

ENTRYPOINT java -jar app.jar