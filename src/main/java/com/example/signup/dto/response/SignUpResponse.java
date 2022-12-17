package com.example.signup.dto.response;


import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class SignUpResponse<D> {

    private final String loginId;
    private final String password;
    private final String name;

}