FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/controlvisitas-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_controlvisitas.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_controlvisitas.jar"]
