package com.litsynp.redisdemo.redis;

import static org.assertj.core.api.Assertions.assertThat;

import com.litsynp.redisdemo.dto.MemberCache;
import com.litsynp.redisdemo.domain.MemberRedisRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisRepositoryTest {

    @Autowired
    private MemberRedisRepository memberRedisRepository;

    @Test
    @DisplayName("Test member Redis repository")
    void memberRepositoryTest() {
        MemberCache memberCache = new MemberCache("Test", 20);

        // Save member in Redis
        // Member ID is auto-generated in form "member:XXX"
        memberRedisRepository.save(memberCache);

        // Get member from Redis
        Optional<MemberCache> memberOptional = memberRedisRepository.findById(memberCache.getId());
        assertThat(memberOptional).isNotNull();
        System.out.println("member ID = " + memberOptional.get().getId());

        // Count all members in Redis
        var memberCount = memberRedisRepository.count();
        System.out.println("memberCount = " + memberCount);

        // Delete member
        memberRedisRepository.delete(memberCache);
    }
}
