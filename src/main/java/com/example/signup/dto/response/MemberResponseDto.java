package com.example.signup.dto.response;


import com.example.signup.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private final String loginId;
    private final String password;
    private final String name;



}