#!/usr/bin/env bash
docker-compose down
./mvnw clean package
docker-compose up