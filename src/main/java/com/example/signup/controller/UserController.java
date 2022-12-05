package com.example.signup.controller;

import com.example.signup.dto.UserDto;
import com.example.signup.entity.User;
import com.example.signup.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void signup(UserDto userDto){
        userService.signup(userDto);
    }
}
