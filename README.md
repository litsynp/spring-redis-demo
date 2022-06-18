# Spring Redis Demo

## About

This project is created to introduce some different methods and examples to access [Redis](https://redis.io/) from Spring applications.

The project uses two methods to access Redis:

1. RedisTemplate
2. Redis Repository

## Requirements

This project set up with [Docker](https://www.docker.com/) and [docker-compose](https://docs.docker.com/compose/) to use Redis. If
you aren't using
Docker, then you should [install up Redis](https://redis.io/docs/getting-started/) on your own.

## Versions

The versions used in the project are:

- Spring Boot 2.7.0
- Java 17
- Gradle 7
- Redis 6.2

## Spring Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data Redis
- Spring Boot Configuration Processor
- Project Lombok

## Run the servers

The guide provided below is written assuming that you are on a Mac or any Linux machine.

1. Grant permission to shell scripts.
    ```sh
    $ chmod 700 ./run-redis-server.sh
    $ chmod 700 ./run-redis-cli.sh
    ```

2. Run the Redis server.

    ```sh
    $ ./run-redis-server.sh
    ```

3. Run the Spring Boot application server using Gradle.

   ```sh
   $ ./gradlew bootRun
   ```

4. Execute Redis CLI.
   ```sh
   $ ./run-redis-cli.sh
   ```

If you are done with whatever you do, then close the server and remove the docker container.

   ```sh
   $ ./stop-redis-server.sh
   ```
