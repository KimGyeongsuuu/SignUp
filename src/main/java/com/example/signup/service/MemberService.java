package com.example.signup.service;

import com.example.signup.dto.request.MemberSignInRequestDto;
import com.example.signup.dto.request.MemberSignUpRequestDto;
import com.example.signup.dto.response.TokenResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    Integer signUp (MemberSignUpRequestDto requestDto);
    TokenResponseDto login(MemberSignInRequestDto requestDto);
    TokenResponseDto issueAccessToken(HttpServletRequest request);


}
