FROM eclipse-temurin:17-jdk-alpine

COPY target/*.jar drillang-back.jar
ENTRYPOINT ["java","-jar","/drillang-back.jar","--spring.profiles.active=docker"]
EXPOSE 8081/tcp