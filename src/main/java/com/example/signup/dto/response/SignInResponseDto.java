package com.example.signup.dto.response;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class SignInResponseDto<D> {

    private String accessToken;
    private String refreshToken;
}