package com.litsynp.redisdemo.ex4jpacache.service;

import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import com.litsynp.redisdemo.ex4jpacache.exception.MemberNotFoundException;
import com.litsynp.redisdemo.ex4jpacache.repository.MemberRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public Member create(Member member) {
        return memberRepository.save(member);
    }

    public Member update(UUID memberId, Member member) {
        Member existingMember = findById(memberId);
        existingMember.update(member.getName(), member.getAge());
        return existingMember;
    }

    public void deleteById(UUID memberId) {
        Member existingMember = findById(memberId);
        memberRepository.delete(existingMember);
    }
}
