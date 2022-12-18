package com.example.signup.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", 404),
    EXIST_MEMBER_ID("이미 존재하는 Id 입니다", 404),

    MISMATCH_MEMBER_PASSWORD("비밀번호가 일치하지 않습니다.", 400);

    private final String errorMessage;
    private final int status;
}