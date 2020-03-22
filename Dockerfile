FROM openjdk:11
MAINTAINER mateus@mail.com
WORKDIR /app
EXPOSE 8080
ADD ./build/libs/operations-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "operations-0.0.1-SNAPSHOT.jar"]