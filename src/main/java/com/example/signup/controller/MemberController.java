package com.example.signup.controller;

import com.example.signup.dto.request.SignInDto;
import com.example.signup.dto.request.SignUpDto;
import com.example.signup.dto.response.SignUpResponse;
import com.example.signup.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;


    @PostMapping("/signup")
    public SignUpResponse<Object> signup(@Validated @RequestBody SignUpDto signUpDto){
        memberService.signUp(signUpDto);
        return SignUpResponse.of(signUpDto.getLoginId(),signUpDto.getPassword(),signUpDto.getName());
    }

    @PostMapping("/signin")
    public void sigin(@Validated @RequestBody SignInDto signInDto){
        memberService.signin(signInDto);
    }



//    @PatchMapping("/update/{memberId}")
//    public void update(@PathVariable Integer memberId, @RequestBody SignUpDto signUpRequest){
//        memberService.update(memberId,signUpRequest);
//    }
//
//    @GetMapping("/list")
//    public List<Member> list(){
//        return memberService.memberList();
//    }

}
