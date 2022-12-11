package com.example.signup.exception;


import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{

    private final Error error;

    public MemberNotFoundException(String message) {
        super(message);
        this.error = Error.MEMBER_NOT_FOUND;
    }
}