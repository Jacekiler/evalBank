FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN adduser -S appuser -u 1001
USER appuser
ENTRYPOINT ["java","-jar","/app.jar"]