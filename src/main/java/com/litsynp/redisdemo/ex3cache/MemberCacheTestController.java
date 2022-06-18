package com.litsynp.redisdemo.ex3cache;

import com.litsynp.redisdemo.ex1redistemplate.MemberCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v3/members")
@Slf4j
public class MemberCacheTestController {

    @GetMapping
    @Cacheable(value = "member", cacheManager = "cacheManager")
    public MemberCache get(@RequestParam("id") String id) {
        log.info("get member - memberId={}", id);

        // Gets faster next time with cache
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new MemberCache(id, "test", 20);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "member", cacheManager = "cacheManager")
    public void delete(@PathVariable String id) {
        log.info("delete member - memberId={}", id);
    }
}
