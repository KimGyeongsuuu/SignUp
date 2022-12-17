package com.example.signup.dto.response;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class LoginResponse<D> {

    private String accessToken;
    private String refreshToken;
}