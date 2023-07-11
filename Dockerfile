FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD target/serviceorganisation*.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]