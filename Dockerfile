FROM openjdk:8
ADD target/caronlinesalesappdocker.jar caronlinesalesappdocker.jar
ENTRYPOINT ["java", "-jar","caronlinesalesappdocker.jar"]
EXPOSE 8080