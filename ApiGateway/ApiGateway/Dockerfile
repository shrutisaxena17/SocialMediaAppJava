FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/ApiGateway-0.0.1-SNAPSHOT.jar apiGateway.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "apiGateway.jar"]
