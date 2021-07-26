FROM openjdk:8-jdk-alpine

COPY target/codenames-backend.jar /var/app/codenames-demo.jar

CMD ["/usr/bin/java", "-Xmx1G", "-jar", "/var/app/codenames-demo.jar"]
