package com.example.signup.repository;

import com.example.signup.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByLoginId(String loginId);
    boolean existsById(String loginId);

}
