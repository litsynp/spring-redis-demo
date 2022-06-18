package com.litsynp.redisdemo.ex2redisrepo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * Used for testing with Redis repository
 */
@RedisHash(value = "member")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisMember implements Serializable {

    @Id
    private String id;
    private String name;
    private Integer age;

    private LocalDateTime createdAt;

    public RedisMember(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public RedisMember(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
}
