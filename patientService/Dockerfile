FROM maven:3-amazoncorretto-8-debian as builder

WORKDIR /app
COPY pom.xml .

LABEL authors="ladeologun"

ENTRYPOINT ["top", "-b"]