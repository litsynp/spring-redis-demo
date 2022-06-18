package com.litsynp.redisdemo.ex2redisrepo;

import org.springframework.data.repository.CrudRepository;

public interface MemberRedisRepository extends CrudRepository<RedisMember, String> {

}
