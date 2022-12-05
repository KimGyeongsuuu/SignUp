package com.example.signup.service;

import com.example.signup.dto.UserDto;
import com.example.signup.entity.User;
import com.example.signup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setPassword(userDto.getPassword());
        user.setPassword2(userDto.getPassword2());
        user.setName(userDto.getName());

        userRepository.save(user);
    }


}
