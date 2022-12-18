package com.example.signup.controller;

import com.example.signup.dto.request.MemberSignInRequestDto;
import com.example.signup.dto.request.MemberSignUpRequestDto;
import com.example.signup.dto.response.TokenResponseDto;
import com.example.signup.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping("/signup")
    public Integer signup(@RequestBody @Valid MemberSignUpRequestDto signUpDto){
       return memberService.signUp(signUpDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid MemberSignInRequestDto signInDto) {
        return memberService.login(signInDto);
    }

    //TODO : Access Token 이 만료가 되면 여기로 요청을 보냄
    @PutMapping("/newAccess")
    public TokenResponseDto issueAccessToken(HttpServletRequest request) {
        return memberService.issueAccessToken(request);
    }


}
