FROM openjdk:17-jdk-alpine
MAINTAINER Alexander Khrenov
COPY target/pastebox-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java", "-jar", "/pastebox.jar"]