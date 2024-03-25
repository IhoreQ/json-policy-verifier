FROM maven:3.8.4-openjdk-17 AS builder

COPY pom.xml .

COPY src /src

RUN mvn clean package