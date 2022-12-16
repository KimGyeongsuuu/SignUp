package com.example.signup.exception;


import lombok.Getter;

@Getter
public class PasswordException extends RuntimeException{
    private final Error error;

    public PasswordException(String message) {
        super(message);
        this.error = Error.MISMATCH_MEMBER_PASSWORD;
    }
}
