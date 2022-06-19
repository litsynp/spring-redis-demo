package com.litsynp.redisdemo.ex4jpacache.service;

import com.litsynp.redisdemo.ex4jpacache.cache.CachedPage;
import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCacheService implements MemberService {

    private final MemberServiceImpl memberService;

    @Cacheable(value = "member", key = "#memberId", cacheManager = "cacheManager")
    public Member findById(UUID memberId) {
        return memberService.findById(memberId);
    }

    @Cacheable(value = "member", cacheManager = "cacheManager")
    public Page<Member> findAll(Pageable pageable) {
        // Resolves error in deserialization of PageImpl
        return new CachedPage<>(memberService.findAll(pageable));
    }

    public Member create(Member member) {
        return memberService.create(member);
    }

    @CachePut(value = "member", key = "#memberId", cacheManager = "cacheManager")
    public Member update(UUID memberId, Member member) {
        return memberService.update(memberId, member);
    }

    @CacheEvict(value = "member", key = "#memberId", cacheManager = "cacheManager")
    public void deleteById(UUID memberId) {
        memberService.deleteById(memberId);
    }
}
