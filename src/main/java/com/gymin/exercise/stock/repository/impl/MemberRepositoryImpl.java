package com.gymin.exercise.stock.repository.impl;

import com.gymin.exercise.stock.model.Member;
import com.gymin.exercise.stock.repository.MemberRepository;
import com.gymin.exercise.stock.repository.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        log.info("save member");

        memberMapper.save(member);
        return member;
    }

    @Override
    public Member findByLoginId(String loginId) {

        return memberMapper.findByLoginId(loginId);
    }
}
