package com.gymin.exercise.stock.repository.mapper;

import com.gymin.exercise.stock.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findByLoginId(String LoginId);
}
