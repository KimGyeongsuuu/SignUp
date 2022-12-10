package com.example.signup.dto.request;

import com.example.signup.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    @NotEmpty(message = "아이디를 입력해주세요")
    @Size(min = 3,max=10,message = "아이디는 2자 이상 10자 이하로 입력해주세요.")
    private String loginId;
    @NotEmpty
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하로 입력해주세요.")
    private String password;
    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    public User toEntity(){
        return User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .build();
    }


}