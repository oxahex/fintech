FROM openjdk:11
RUN mkdir -p deploy
WORKDIR /deploy
COPY ./build/libs/user-0.0.1.jar user-api.jar
ENTRYPOINT ["java", "-jar", "/deploy/user-api.jar"]