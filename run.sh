#!/bin/sh
set -a
[ -f /app/.env ] && . /app/.env
set +a
exec java -jar app.jar
