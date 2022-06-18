package com.litsynp.redisdemo.ex2redisrepo;

import static org.assertj.core.api.Assertions.assertThat;

import com.litsynp.redisdemo.ex2redisrepo.MemberRedisRepository;
import com.litsynp.redisdemo.ex2redisrepo.RedisMember;
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
        RedisMember member = new RedisMember("Test", 20);

        // Save member in Redis
        // Member ID is auto-generated in form "member:XXX"
        memberRedisRepository.save(member);

        // Get member from Redis
        Optional<RedisMember> memberOptional = memberRedisRepository.findById(member.getId());
        assertThat(memberOptional).isNotNull();
        System.out.println("member ID = " + memberOptional.get().getId());

        // Count all members in Redis
        var memberCount = memberRedisRepository.count();
        System.out.println("memberCount = " + memberCount);

        // Delete member
        memberRedisRepository.delete(member);
    }
}
