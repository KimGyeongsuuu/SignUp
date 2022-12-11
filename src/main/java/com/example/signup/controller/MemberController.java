package com.example.signup.controller;

import com.example.signup.dto.request.LoginRequest;
import com.example.signup.dto.request.SignUpRequest;
import com.example.signup.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody @Validated SignUpRequest signUpRequest){
        memberService.signUp(signUpRequest);
        return "SignupSUCCESS";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        log.info("userId = {},password = {}",loginRequest.getLoginId(),loginRequest.getPassword());
        memberService.login(loginRequest);
        return "LoginSUCESS";
    }


}
