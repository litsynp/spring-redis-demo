package com.litsynp.redisdemo.ex4jpacache.repository;

import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, UUID> {

}
