FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY /build/libs/Pizzeria-0.0.1-SNAPSHOT-plain.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


