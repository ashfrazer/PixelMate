FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/PixelMate.jar /app/PixelMate.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/PixelMate.jar"]