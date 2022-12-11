package com.example.signup.service;

import com.example.signup.dto.request.LoginRequest;
import com.example.signup.dto.request.SignUpRequest;
import com.example.signup.entity.Member;
import com.example.signup.exception.MemberNotFoundException;
import com.example.signup.exception.PasswordException;
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
    private final SignUpRequest memberRequest;

    @Transactional
    public void signUp (SignUpRequest signUpRequest) {
        Member account = Member.builder()
                .loginId(signUpRequest.getLoginId())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .build();
        memberRepository.save(account);
    }
    @Transactional
    public void login (LoginRequest loginRequest){
        Member member = memberRepository.findByLoginId(loginRequest.getLoginId())
                .orElseThrow(()->new MemberNotFoundException("존재하지 않는 회원입니다."));

        if (loginRequest.getPassword().equals(memberRequest.getPassword())){
            throw new PasswordException("비밀번호가 일치하지 않습니다.");
        }

    }
}
