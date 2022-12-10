package com.example.signup.controller;

import com.example.signup.dto.request.RequestDto;
import com.example.signup.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody @Validated RequestDto requestDto){
        memberService.signUp(requestDto);
        return "SUCCESS";
    }


}
