package com.example.signup.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", 404),
    MISMATCH_MEMBER_PASSWORD("비밀번호가 일치하지 않습니다.", 400);

    private String errorMessage;
    private int status;
}