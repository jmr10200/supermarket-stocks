package com.gymin.exercise.stock.service;

import com.gymin.exercise.stock.model.Member;
import com.gymin.exercise.stock.repository.MemberRepository;
import com.gymin.exercise.stock.utils.EncodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member saveMember(Member member) {
        // 등록되어있는 아이디 확인
        Member byLoginId = memberRepository.findByLoginId(member.getLoginId());
        if (!Objects.isNull(byLoginId)) {
            // 등록된 유저가 있으면 null 리턴
            return null;
        }
        // 암호화
        String encodedPw = EncodeUtils.sha256Encode(member.getPassword());
        member.setPassword(encodedPw);
        Member savedMember = memberRepository.save(member);

        return savedMember;
    }
}
