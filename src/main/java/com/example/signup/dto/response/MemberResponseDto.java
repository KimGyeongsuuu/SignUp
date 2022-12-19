package com.example.signup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private final String loginId;
    private final String password;
    private final String name;



}