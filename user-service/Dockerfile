FROM openjdk:8-jdk-alpine

#Postgres properties
ENV POSTGRES_URL="localhost:5432"
ENV POSTGRES_DATABASE_NAME="sshort"
ENV POSTGRES_USERNAME="sshort"
ENV POSTGRES_PASSWORD="sshort"

#link service properties
ENV LINK_SERVICE_URL=""

LABEL maintainer="Sshort"

EXPOSE 8080

ARG JAR_FILE=target/user-service-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} sshort-user-service.jar

ENTRYPOINT ["java", "-jar", "/sshort-user-service.jar"]