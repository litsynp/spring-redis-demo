# Spring Redis Demo

## About

This project is created to introduce some different methods and examples to access [Redis](https://redis.io/) from Spring applications.

The project uses 3 methods to access Redis:

1. [RedisTemplate and opsForValue()](https://www.baeldung.com/spring-data-redis-properties)
2. [Redis Repository](https://www.baeldung.com/spring-data-redis-tutorial#redis-repository)
3. [Spring Cache Abstraction](https://www.baeldung.com/spring-cache-tutorial) (`@Cacheable`, `@CachePut`, `@CacheEvict`, and etc.)

## Requirements

This project set up with [Docker](https://www.docker.com/) and [docker-compose](https://docs.docker.com/compose/) to use Redis. If you aren't using Docker, then you should [install up Redis](https://redis.io/docs/getting-started/) on your own.

## Versions

The versions used in the project are:

- Spring Boot 2.7.0
- Java 17
- Gradle 7
- Redis 6.2

## Spring Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data Redis
- Spring Boot Starter Data JPA
- Spring Boot Configuration Processor
- Spring Boot Starter Test
- Project Lombok
- H2 Database
- `com.fasterxml.jackson.datatype:jackson-datatype-jsr310` for Date and Time Serialization and Deserialization

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

**I recommend testing and experimenting with tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/) yourself.**

If you are done with whatever you do, then close the server and remove the docker container.

```sh
$ ./stop-redis-server.sh
```

## Shell Scripts and Docker

- `docker-compose-redis.yml`: Defines Redis service. Sets The redis password and port and registers `redis.conf` via volume.
- `redis.conf`: Configurations for Redis service. Turns off [RDB and AOF](https://stackoverflow.com/questions/28785383/how-to-disable-persistence-with-redis) features. Disables [potentially dangerous commands](https://programmer.group/redis-disable-dangerous-command.html). Sets max client to 50,000.
- `run-redis-server.sh`: A shell script to stop a running Redis service and builds & runs a new one.
- `run-redis-cli.sh`: A shell script to allow access to Redis via Redis CLI.
    - Make sure to remove line `--include /usr/local/etc/redis/redis.conf` from `docker-compose-redis.yml`, **if you want to use commands like `keys *` and `get <key>`** to see keys and values put in Redis.
- `stop-redis-server.sh`: A shell script to stop and remove Redis service.

## Packages and Classes

- `resources/application.yml`: Spring Boot configurations. Configures Redis with host, port and password, and JPA with datasource URL, username and password. Enables H2 console via `/h2-console`.

- `com.litsynp.redisdemo`
    - `config`: Holds configurations, especially for Redis.
        - `ConfigurationPropertiesConfig`: Enables configuration properties.
        - `ObjectMapperConfig`: Provides customized object mapper for JSON.
        - `RedisCacheConfig`: Configures caching with Redis. Basically it creates a `CacheManager`to use in caching member. This is used in `com.litsynp.redisdemo.ex4jpacache`. Note that the cache manager registers a customized `ObjectMapper` as its value serializer. The customized `ObjectMapper` registers `JavaTimeModule` provided by `com.fasterxml.jackson.datatype:jackson-datatype-jsr310` for serialization and deserialization of `LocalDateTime` present in member entity. The entry TTL is set to 3 minutes.
        - `RedisConfig`: Configures `RedisConnectionFactory` and `RedisTemplate` beans.
    - `ex1redistemplate`: Example of `RedisTemplate` and `opsForValue`.
        - `MemberCache`: An example class to be instanced and cached. Currently, this is only used in test codes.
        - `RedisController`: Uses `StringRedisTemplate` to put String key and String value to Redis.
    - `ex2redisrepo`: Uses CrudRepository for caching member. Currently, this is only used in test codes.
        - `MemberRedisRepository`: The Redis repository for `RedisMember`.
        - `RedisMember`: Example member class to be cached. Annotated with `@RedisHash(value = "member")` to be saved in "member:key" format.
    - `ex3cache`: An example of how caching with Redis can be done so simply with **cache
      abstraction**.
        - `MemberCacheTestController`: Note that there is `Thread.sleep(1500)` in the **GET** API. The API is annotated with `@Cacheable(value = "member", cacheManager = "cacheManager")`, caching the response of the API. It gets faster the second time you request the same API.
    - `ex4jpacache`: This example shows how Spring Data JPA and Redis can work together to bring faster responses from APIs, with **cache abstraction**.
        - `api`: API controllers for comparison.
            - `MemberApiController`: Member API controller, **not cached**.
            - `MemberCachedApiController`: Member API controller, **cached with Redis**.
        - `domain.Member`: Member JPA entity. Saved in `member` table.
        - `dto.*`: DTOs to be used in API controllers. No further explanations needed.
        - `exception`: Exceptions thrown from controllers/services.
        - `repository.MemberRepository`: Spring Data JPA Repository for member entity.
        - `service`: Member service interface and its implementations.
            - `MemberService`: Interface for service for member business logics.
            - `MemberServiceImpl`: Includes Spring Data JPA business logic implementations.
            - `MemberCacheService`: Calls methods in `MemberServiceImpl` with exactly the same method signatures. The only difference is that the results are **cached**.
        - `cache.CachedPage`: Wrapper class for `PageImpl`, used for pagination. Without this, you will get `InvalidDefinitionException` when calling `/members` API twice. More on this [here](https://stackoverflow.com/questions/55965523/error-during-deserialization-of-pageimpl-cannot-construct-instance-of-org-spr).

## APIs

- `/v1/str-str` (`RedisController`): Example API for putting and getting String key and String value to Redis.
- ```
  Request:
  POST /v1/str-str
  {
    "key": "hello",
    "value": "world"
  }

  Response:
  201 Created
  ```

- ```
  Request:
  GET /v1/str-str/{id}

  Response:
  200 OK
  {
    "key": "hello",
    "value": "world",
    "success": "true"
  }
  ```

- `/v3/members` (`MemberCacheTestController`): Example to save member cache to Redis.
- ```
  Request:
  GET /v3/members?id={id}
  Saves member with id to Redis.

  Response:
  200 OK
  {
    "id": "XXXXXXX",
    "name": "test",
    "age": 20,
    "createdAt": "2022-06-20T01:01:01"
  }

  Response:
  201 Created
  ```

- ```
  Request:
  DELETE /v3/members/{id}
  Deletes member cache with `id` from Redis.

  Response:
  204 No Content
  ```

- `/v4/members` (`MemberApiController`): Simple member API using only Spring Data JPA. Not cached. Provides CRUD. API and implementation details on the [source code](https://github.com/litsynp/spring-redis-demo/blob/main/src/main/java/com/litsynp/redisdemo/ex4jpacache/api/MemberApiController.java).

- `/v4/members` (`MemberCachedApiController`): Simple member API using Spring Data JPA and Redis for cache. **Cached**. Provides CRUD. API and implementation details on the [source code](https://github.com/litsynp/spring-redis-demo/blob/main/src/main/java/com/litsynp/redisdemo/ex4jpacache/api/MemberCachedApiController.java).
