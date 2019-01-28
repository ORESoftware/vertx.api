#!/usr/bin/env bash

set -e;

docker network create vertx | cat

docker build -t vertx.api .

docker stop vertx.api | cat
docker rm vertx.api | cat


docker run --name vertx.api --net=vertx -p 3005:3005 -it vertx.api
