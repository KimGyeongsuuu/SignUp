package com.example.signup.exception;


import lombok.Getter;

@Getter
public class PasswordNotMatch extends RuntimeException{
    private final ErrorCode error;

    public PasswordNotMatch(String message) {
        super(message);
        this.error = ErrorCode.MISMATCH_MEMBER_PASSWORD;
    }
}
