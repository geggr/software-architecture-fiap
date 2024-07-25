FROM eclipse-temurin:22

RUN mkdir /opt/app

COPY src /opt/app/src
COPY .mvn /opt/app/.mvn
COPY mvnw /opt/app
COPY pom.xml /opt/app

WORKDIR /opt/app
CMD ["./mvnw", "spring-boot:run"]
