FROM eclipse-temurin:19-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notification.jar
EXPOSE 1232
ENTRYPOINT ["java","-jar","/notification.jar"]