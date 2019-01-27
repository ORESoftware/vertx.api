#!/usr/bin/env bash

export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

#export MAVEN_OPTS="-agentpath:/home/oleg/.IntelliJIdea2018.3/config/plugins/jr-ide-idea/lib/jrebel6/lib/libjrebel64.so $MAVEN_OPTS"
#mvn -offline -T 9 package exec:java -DskipTests

mvn -offline -T 9 package exec:java -DskipTests -Dmaven.install.skip=true


