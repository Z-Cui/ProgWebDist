FROM openjdk:8
ADD ./build/libs/userservice-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "user-service.jar"]