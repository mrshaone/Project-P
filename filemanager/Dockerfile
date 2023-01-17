FROM eclipse-temurin:19-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} filemanager.jar
EXPOSE 1231
ENTRYPOINT ["java","-jar","/filemanager.jar"]
RUN mkdir -p uploads