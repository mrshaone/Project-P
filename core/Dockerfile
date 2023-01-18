FROM eclipse-temurin:19-jdk-alpine as builder
WORKDIR /opt/core
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:19-jre-alpine
WORKDIR /opt/core
EXPOSE 1230
COPY --from=builder /opt/core/target/*.jar /opt/core/*.jar
ENTRYPOINT [ "java", "-jar", "/opt/core/*.jar" ]