FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./src/main/back-end_programming-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "back-end_programming-0.0.1-SNAPSHOT.jar"]
