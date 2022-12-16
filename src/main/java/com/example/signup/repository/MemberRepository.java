package com.example.signup.repository;

import com.example.signup.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByMemberId(Integer memberId);

    boolean existsMemberByLoginId(String loginId);



}
