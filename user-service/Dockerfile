FROM openjdk:8-jre-alpine
# jar path should be parameterized (version in file may vary)
COPY target/user-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
