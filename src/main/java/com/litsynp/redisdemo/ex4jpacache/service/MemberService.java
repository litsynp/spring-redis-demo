package com.litsynp.redisdemo.ex4jpacache.service;

import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Member findById(UUID memberId);

    Page<Member> findAll(Pageable pageable);

    Member create(Member member);

    Member update(UUID memberId, Member member);

    void deleteById(UUID memberId);
}
