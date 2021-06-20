FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} codenames-demo.jar
ENTRYPOINT ["java", "-jar", "/codenames-demo.jar"]