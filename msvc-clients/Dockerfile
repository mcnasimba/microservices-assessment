FROM openjdk:21-jdk AS builder

ARG MSVC_NAME=msvc-clients

WORKDIR /app/$MSVC_NAME

COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN sed -i -e 's/\r$//' ./mvnw

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package

FROM openjdk:21-jdk

ARG MSVC_NAME=msvc-clients
ARG TARGET_FOLDER=/app/$MSVC_NAME/target

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder $TARGET_FOLDER/msvc-clients-0.0.1-SNAPSHOT.jar .

EXPOSE 3001

CMD ["java", "-jar", "msvc-clients-0.0.1-SNAPSHOT.jar"]