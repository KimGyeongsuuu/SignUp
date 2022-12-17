package com.example.signup.service;

import com.example.signup.dto.request.SignInDto;
import com.example.signup.dto.request.SignUpDto;
import com.example.signup.entity.Member;

import java.util.List;

public interface MemberService {
    void signin (SignInDto loginRequest);
    void signUp (SignUpDto signUpRequest);


    //List<Member> memberList();
    //void update(Integer memberId, SignUpDto signUpRequest);
}
