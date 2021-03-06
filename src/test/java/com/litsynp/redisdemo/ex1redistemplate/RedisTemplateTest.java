package com.litsynp.redisdemo.ex1redistemplate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, MemberCache> memberRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    @DisplayName("Set string:string")
    void stringValue_ok() {
        // given
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        // when
        valueOperations.set("red", "🍎apple");
        valueOperations.set("yellow", "🍌banana");
        valueOperations.set("green", "🍈melon");

        System.out.println("red in Redis = " + valueOperations.get("red"));
        System.out.println("yellow in Redis = " + valueOperations.get("yellow"));
        System.out.println("green in Redis = " + valueOperations.get("green"));

        // then
        assertThat(valueOperations.get("red")).isEqualTo("🍎apple");
        assertThat(valueOperations.get("yellow")).isEqualTo("🍌banana");
        assertThat(valueOperations.get("green")).isEqualTo("🍈melon");
    }

    @Test
    @DisplayName("Test string:object")
    void objectValue() {
        // given
        ValueOperations<String, MemberCache> valueOperations = memberRedisTemplate.opsForValue();
        MemberCache memberCache = new MemberCache("1", "Test", 20);
        String key = "member:" + memberCache.getId();
        valueOperations.set(key, memberCache);

        MemberCache memberCacheRetrieved = valueOperations.get(key);
        System.out.println("member.id = " + memberCacheRetrieved.getId());

        assertThat(memberCacheRetrieved.getId()).isEqualTo(memberCache.getId());
    }
}
