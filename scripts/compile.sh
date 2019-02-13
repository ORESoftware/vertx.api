#!/usr/bin/env bash

m2="$HOME/.m2/repository/huru/vertx-start-project/1.0-SNAPSHOT/vertx-start-project-1.0-SNAPSHOT.jar"

m2="/home/oleg/codes/oresoftware/vertx.api/.mvn/wrapper/maven-wrapper.jar"


m2="/home/oleg/codes/oresoftware/vertx.api/target/vertx-start-project-1.0-SNAPSHOT-fat.jar"


rm -rf "$PWD/target/classes/huru"
mkdir -p "$PWD/target/classes/huru";

#
#javac -d "$PWD/target/classes" \
#      -cp "$CLASSPATH:$PWD/src/main/java:$m2" \
#      "$(find "$PWD/src/main/java/huru" -name '*.java')"



#javac -d "$PWD/target/classes" \
#      -cp "$CLASSPATH:$PWD/src/main/java:$m2" \
#        "@$PWD/huru.files"


javac -d "$PWD/target/classes" \
      -cp "$CLASSPATH:$PWD/src/main/java:$m2" \
        "/home/oleg/codes/oresoftware/vertx.api/src/main/java/huru/MainVerticle.java"
