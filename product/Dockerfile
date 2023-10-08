FROM openjdk:11
RUN mkdir -p deploy
WORKDIR /deploy
COPY ./build/libs/product-0.0.1.jar product-api.jar
ENTRYPOINT ["java", "-jar", "/deploy/product-api.jar"]