FROM openjdk:10-jre-slim

COPY target/vertx-start-project-1.0-SNAPSHOT-fat.jar fat.jar

ENTRYPOINT java -jar fat.jar
