package com.litsynp.redisdemo.domain;

import org.springframework.data.repository.CrudRepository;

public interface MemberRedisRepository extends CrudRepository<Member, String> {

}
