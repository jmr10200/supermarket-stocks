package com.gymin.exercise.stock.repository;

import com.gymin.exercise.stock.model.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findByLoginId(String loginId);
}
