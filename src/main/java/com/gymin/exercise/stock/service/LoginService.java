package com.gymin.exercise.stock.service;

import com.gymin.exercise.stock.model.Member;
import com.gymin.exercise.stock.repository.MemberRepository;
import com.gymin.exercise.stock.utils.EncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * @return null 이면 로그인 실패
     */
    public Member login(String loginId, String password) {
        Member savedMember = memberRepository.findByLoginId(loginId);
        if (savedMember == null) {
            return null;
        }
        // DB에 등록되어있는 패스워드
        String savedPw = savedMember.getPassword();
        // 입력한 패스워드
        String targetPw = EncodeUtils.sha256Encode(password);
        // 패스워드 일치 확인
        if (!Objects.isNull(savedMember)) {
            if (savedPw.equals(targetPw)) {
                return savedMember;
            } else {
                return null;
            }
        }

        return savedMember;
    }
}
