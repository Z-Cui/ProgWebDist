FROM openjdk:8
ADD ./build/libs/bookmarkdb-0.0.1-SNAPSHOT.jar bookmark-database.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "bookmark-database.jar"]
