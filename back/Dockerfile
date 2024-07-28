FROM adoptopenjdk:17-jdk-hotspot AS build
WORKDIR /app
COPY . .
RUN ./mvn clean package

FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY --from=build /app/build/libs/takeMeHome.jar takeMeHome.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "takeMeHome.jar"]
