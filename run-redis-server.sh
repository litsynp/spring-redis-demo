#!/bin/sh
docker-compose -f docker-compose-redis.yml down -v &&
  docker-compose -f docker-compose-redis.yml up -d --build
