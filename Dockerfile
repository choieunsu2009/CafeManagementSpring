FROM amazoncorretto:21
LABEL authors="choies"
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/*-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]