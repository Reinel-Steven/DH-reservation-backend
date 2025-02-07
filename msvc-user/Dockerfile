FROM openjdk:17-jdk-alpine as builder
LABEL authors="ReinelSteven"
WORKDIR /app/msvc-user

COPY ./pom.xml /app
COPY ./msvc-user/.mvn ./.mvn
COPY ./msvc-user/mvnw .
COPY ./msvc-user/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./msvc-user/src ./src

RUN ./mvnw clean package -DskipTests8

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/msvc-user/target/msvc-user-0.0.1-SNAPSHOT.jar .

EXPOSE 8002
ENTRYPOINT ["java", "-jar", "msvc-user-0.0.1-SNAPSHOT.jar"]