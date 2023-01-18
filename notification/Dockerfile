FROM eclipse-temurin:19-jdk-alpine as builder
WORKDIR /opt/notification
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:19-jre-alpine
WORKDIR /opt/notification
EXPOSE 1232
COPY --from=builder /opt/notification/target/*.jar /opt/notification/*.jar
ENTRYPOINT [ "java", "-jar", "/opt/notification/*.jar" ]