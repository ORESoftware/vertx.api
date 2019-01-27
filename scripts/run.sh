#!/usr/bin/env bash

#export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
mvn -offline -T 9 package exec:java -DskipTests

