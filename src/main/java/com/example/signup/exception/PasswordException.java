package com.example.signup.exception;


import lombok.Getter;

@Getter
public class PasswordException extends RuntimeException{
    private final ErrorCode error;

    public PasswordException(String message) {
        super(message);
        this.error = ErrorCode.MISMATCH_MEMBER_PASSWORD;
    }
}
