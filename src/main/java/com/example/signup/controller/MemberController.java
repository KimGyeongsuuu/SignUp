package com.example.signup.controller;

import com.example.signup.dto.request.LoginRequest;
import com.example.signup.dto.request.SignUpRequest;
import com.example.signup.dto.response.LoginResponse;
import com.example.signup.dto.response.SignUpResponse;
import com.example.signup.entity.Member;
import com.example.signup.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public SignUpResponse<Object> signup(@RequestBody @Validated SignUpRequest signUpRequest){
        memberService.signUp(signUpRequest);
        return SignUpResponse.of(signUpRequest.getLoginId(),signUpRequest.getPassword(),signUpRequest.getName());
    }

    @PostMapping("/login")
    public LoginResponse<Object> login(@RequestBody LoginRequest loginRequest){
        memberService.login(loginRequest);
        return LoginResponse.of(loginRequest.getLoginId(),loginRequest.getPassword());
    }

    @PatchMapping("/update/{memberId}")
    public void update(@PathVariable Integer memberId, @RequestBody SignUpRequest signUpRequest){
        memberService.update(memberId,signUpRequest);
    }

    @GetMapping("/list")
    public List<Member> list(){
        return memberService.memberList();
    }

}
