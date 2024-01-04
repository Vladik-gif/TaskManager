#Build Gradle
FROM gradle:8.1-amazoncorretto-20 AS builder
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN gradle build --no-daemon

#Build Java
FROM amazoncorretto:20-al2-full as final
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar /backend.jar
ENTRYPOINT ["java","-jar", "/backend.jar"]