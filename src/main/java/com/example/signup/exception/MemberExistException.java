package com.example.signup.exception;


import lombok.Getter;

@Getter
public class MemberExistException extends RuntimeException{

    private final Error error;

    public MemberExistException(String message) {
        super(message);
        this.error = Error.EXIST_MEMBER_ID;
    }
}
