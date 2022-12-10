package com.example.signup.service;

import com.example.signup.dto.request.RequestDto;
import com.example.signup.entity.Member;
import com.example.signup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void signUp (RequestDto requestDto) {
        Member account = Member.builder()
                .loginId(requestDto.getLoginId())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .build();
        memberRepository.save(account);
    }


}
