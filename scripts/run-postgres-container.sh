#!/usr/bin/env bash

set -e;

docker stop vertx.postgres | cat
docker rm vertx.postgres | cat

 # -p 5432:5432

docker run --net=vertx  \
           --name vertx.postgres \
           -e POSTGRES_PASSWORD=postgres \
           postgres
