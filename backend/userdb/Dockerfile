FROM openjdk:8
ADD ./build/libs/userdb-0.0.1-SNAPSHOT.jar user-database.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "user-database.jar"]