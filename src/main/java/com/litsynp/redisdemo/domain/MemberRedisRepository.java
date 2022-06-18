package com.litsynp.redisdemo.domain;

import com.litsynp.redisdemo.dto.RedisMember;
import org.springframework.data.repository.CrudRepository;

public interface MemberRedisRepository extends CrudRepository<RedisMember, String> {

}
