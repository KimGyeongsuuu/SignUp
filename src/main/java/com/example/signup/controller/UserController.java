package com.example.signup.controller;

import com.example.signup.dto.request.RequestDto;
import com.example.signup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody RequestDto userDto){
        userService.signUp(userDto);
        return ;
    }


}
