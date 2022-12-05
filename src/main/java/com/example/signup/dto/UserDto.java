package com.example.signup.dto;

import com.example.signup.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "아이디를 입력해주세요")
    @Size(min = 3,max=10,message = "아이디는 3자 이상 10자 이하로 입력해주세요.")
    private String userId;
    @NotBlank
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;
    @NotBlank
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password2;
    @NotBlank
    private String name;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .password(password)
                .password2(password2)
                .name(name)
                .build();
    }


}
