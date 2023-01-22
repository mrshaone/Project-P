FROM eclipse-temurin:19-jdk-alpine as builder
WORKDIR /opt/rabbitp
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:19-jre-alpine
WORKDIR /opt/rabbitp
EXPOSE 1233
COPY --from=builder /opt/rabbitp/target/*.jar /opt/rabbitp/*.jar
ENTRYPOINT [ "java", "-jar", "/opt/rabbitp/*.jar" ]