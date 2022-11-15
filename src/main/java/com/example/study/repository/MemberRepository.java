package com.example.study.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface MemberRepository {
    void save(Member member);
    String findById(Long id);

    void clear();

    int GetMemberCount();
}
