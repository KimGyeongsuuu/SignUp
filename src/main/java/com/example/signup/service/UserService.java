package com.example.signup.service;

import com.example.signup.dto.request.RequestDto;
import com.example.signup.entity.User;
import com.example.signup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp (RequestDto requestDto) {
        User account = User.builder()
                .loginId(requestDto.getLoginId())
                .password(requestDto.getPassword())
                .build();
        userRepository.save(account);
    }

}
