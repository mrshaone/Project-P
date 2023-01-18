FROM eclipse-temurin:19-jdk-alpine as builder
WORKDIR /opt/filemanager
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:19-jre-alpine
WORKDIR /opt/filemanager
EXPOSE 1231
COPY --from=builder /opt/filemanager/target/*.jar /opt/filemanager/*.jar
ENTRYPOINT [ "java", "-jar", "/opt/filemanager/*.jar" ]