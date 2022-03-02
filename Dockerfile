FROM openjdk:11
ADD target/ResidualsEngine-0.0.1-SNAPSHOT.jar ResidualsEngine.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/ResidualsEngine.jar"]