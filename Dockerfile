FROM openjdk:17-slim

COPY target/coblan-1.0.0.jar /app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
