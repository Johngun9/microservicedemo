FROM openjdk:8
EXPOSE 8080
ADD target/docker-jenkins-integration-microservicedemo.jar docker-jenkins-integration-microservicedemo.jar
ENTRYPOINT ["java", "-jar", "/docker-jenkins-integration-microservicedemo.jar"]