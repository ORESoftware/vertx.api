#!/usr/bin/env bash


rm -rf "$PWD/target/classes"

#     -source 1.10 \
#     -target 1.10 \

java -jar "$HOME/Desktop/ecj-4.11M1.jar" \
     -source 9 \
     -target 9 \
     -nowarn \
     -d "$PWD/target/classes" \
     -classpath "$CLASSPATH:$PWD/src/main/java:$PWD/target/vertx-start-project-1.0-SNAPSHOT-fat.jar" \
     '@huru.files'
