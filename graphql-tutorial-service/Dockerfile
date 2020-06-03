FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine

ENV APP_HOME="/graphql-tutorial/app"

WORKDIR $APP_HOME

# TODO implement proper versioning
COPY target/graphql-tutorial-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "graphql-tutorial-service-0.0.1-SNAPSHOT.jar"]
