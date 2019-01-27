#!/usr/bin/env bash

export PGPASSWORD='postgres'

psql -h localhost \
     -d oleg \
     -U postgres \
     -p 5432 \
     -a -q -f /home/oleg/codes/huru/huru_api_vertx/src/main/resources/users.sql
