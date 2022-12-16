package com.example.signup.service;

import com.example.signup.dto.request.LoginRequest;
import com.example.signup.dto.request.SignUpRequest;
import com.example.signup.entity.Member;
import com.example.signup.exception.MemberExistException;
import com.example.signup.exception.MemberNotFoundException;
import com.example.signup.exception.PasswordException;
import com.example.signup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    //@Autowired
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public List<Member> memberList(){
        return memberRepository.findAll();
    }

    @Transactional
    public void signUp (SignUpRequest signUpRequest) {

        boolean existMember = memberRepository.existsMemberByLoginId(signUpRequest.getLoginId());

        if (existMember)
                throw new MemberExistException("이미 존재하는 Id 입니다.");
        else{
                Member member = Member.builder()
                        .loginId(signUpRequest.getLoginId())
                        .password(signUpRequest.getPassword())
                        .name(signUpRequest.getName())
                        .build();
                memberRepository.save(member);
        }
    }
    @Transactional
    public void login (LoginRequest loginRequest){
        Member member = memberRepository.findByLoginId(loginRequest.getLoginId())
                .orElseThrow(()->new MemberNotFoundException("존재하지 않은 Id 입니다."));

        boolean matches = loginRequest.getPassword().matches(member.getPassword());

        if (!matches){
            throw new PasswordException("비밀번호가 일치하지 않습니다.");
        }
        log.info("로그인 되었습니다. 회원 id = {} , 비밀번호 = {}",loginRequest.getLoginId(),loginRequest.getPassword());
    }

    @Transactional
    public void update(Integer memberId, SignUpRequest signUpRequest){
        Optional<Member> updateMember = memberRepository.findByMemberId(memberId);
        updateMember.get().updateId(signUpRequest.getLoginId());
        updateMember.get().updatePassword(signUpRequest.getPassword());
        memberRepository.save(updateMember.get());
    }
}
