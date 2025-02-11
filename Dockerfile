# build
FROM maven:3.8.8-amazoncorretto-21-al2023 as build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

#run

FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./despesasapi.jar

EXPOSE 8080
EXPOSE 9090


ENV DATASOURCE_USER=''
ENV DATASOURCE_PASSWORD=''
ENV DATASOURCE_URL=''
ENV DATASOURCE_DRIVER=''

ENV TZ='America/Sao_Paulo'

ENTRYPOINT java -jar despesasapi.jar