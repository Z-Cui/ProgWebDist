FROM openjdk:8
EXPOSE 8761
ADD ./build/libs/eurekaregistry-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java","-jar","eureka.jar"]