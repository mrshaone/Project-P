FROM eclipse-temurin:19-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} core.jar
EXPOSE 1230
ENTRYPOINT ["java","-jar","/core.jar"]