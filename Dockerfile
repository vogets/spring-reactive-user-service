FROM openjdk:8-alpine
COPY target/UserService-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java","-jar","/app.jar"]