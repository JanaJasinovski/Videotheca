FROM openjdk:21
ADD target/Pashas_Task-1.0-SNAPSHOT.jar Pashas_Task.jar
ARG PORT
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "Pashas_Task.jar"]