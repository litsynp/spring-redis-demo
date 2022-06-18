package com.litsynp.redisdemo.domain;

import com.litsynp.redisdemo.dto.MemberCache;
import org.springframework.data.repository.CrudRepository;

public interface MemberRedisRepository extends CrudRepository<MemberCache, String> {

}
