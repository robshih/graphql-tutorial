FROM openjdk:11

ENV APP_HOME="/graphql-tutorial/app"

WORKDIR $APP_HOME

# TODO implement proper versioning
COPY target/graphql-tutorial-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "graphql-tutorial-0.0.1-SNAPSHOT.jar"]
