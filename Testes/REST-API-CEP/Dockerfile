FROM openjdk:14-jdk-alpine
ARG JAR_FILE=target/ramonfacchin-zip.jar
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
