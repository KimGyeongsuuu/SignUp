package com.example.signup.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "member_idx")
    private Integer memberId;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    public void updateId(String loginId){
        this.loginId = loginId;
    }
    public void updatePassword(String password){
        this.password = password;
    }
}
