FROM openjdk:14-alpine

EXPOSE 8080

COPY target/back-end_programming-0.0.1-SNAPSHOT.jar /opt/back-end_programming-0.0.1/lib/back-end_programming-0.0.1-SNAPSHOT.jar
WORKDIR opt/back-end_programming-0.0.1

ENTRYPOINT ["java", ".jar"]
