FROM openjdk:8
EXPOSE 8085
ADD ./build/libs/zuulgateway-0.0.1-SNAPSHOT.jar zuul.jar
ENTRYPOINT ["java","-jar","zuul.jar"]