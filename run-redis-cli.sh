#!/bin/sh
docker-compose -f docker-compose-redis.yml exec redis redis-cli -a testpass
