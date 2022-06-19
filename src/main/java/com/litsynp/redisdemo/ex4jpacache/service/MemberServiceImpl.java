package com.litsynp.redisdemo.ex4jpacache.service;

import com.litsynp.redisdemo.ex4jpacache.domain.Member;
import com.litsynp.redisdemo.ex4jpacache.exception.MemberNotFoundException;
import com.litsynp.redisdemo.ex4jpacache.repository.MemberRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("memberService")
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public Member findById(UUID memberId) {
        log.info("memberRepository.findById({}})", memberId);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    public Page<Member> findAll(Pageable pageable) {
        log.info("memberRepository.findAll()");
        return memberRepository.findAll(pageable);
    }

    public Member create(Member member) {
        log.info("memberRepository.save(member)");
        return memberRepository.save(member);
    }

    public Member update(UUID memberId, Member member) {
        Member existingMember = findById(memberId);
        log.info("existingMember.update({}, {})", member.getName(), member.getAge());
        existingMember.update(member.getName(), member.getAge());
        return existingMember;
    }

    public void deleteById(UUID memberId) {
        Member existingMember = findById(memberId);
        log.info("memberRepository.delete({}})", memberId);
        memberRepository.delete(existingMember);
    }
}
