package com.gymin.exercise.stock.service;

import com.gymin.exercise.stock.model.Member;
import com.gymin.exercise.stock.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    MemberRepository memberRepository;

    public Member findById(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

}
