package com.example.signup.service.impl;

import com.example.signup.dto.request.SignInDto;
import com.example.signup.dto.request.SignUpDto;
import com.example.signup.dto.response.SignInResponseDto;
import com.example.signup.entity.Member;
import com.example.signup.exception.MemberExistException;
import com.example.signup.exception.MemberNotFoundException;
import com.example.signup.exception.PasswordException;
import com.example.signup.repository.MemberRepository;
import com.example.signup.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    //@Autowired
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


//    @Transactional
//    public List<Member> memberList(){
//        return memberRepository.findAll();
//    }

    @Transactional
    @Override
    public void signUp (SignUpDto signUpDto) {

        boolean existMember = memberRepository.existsMemberByLoginId(signUpDto.getLoginId());
        if (existMember)
                throw new MemberExistException("이미 존재하는 Id 입니다.");
        else{
                Member member = Member.builder()
                        .loginId(signUpDto.getLoginId())
                        .password(signUpDto.getPassword())
                        .name(signUpDto.getName())
                        .build();
                memberRepository.save(member);
        }
    }
    @Transactional
    @Override
    public void signin(SignInDto signInDto){
        Member findMember = memberRepository.findByLoginId(signInDto.getLoginId())
                .orElseThrow(()->new MemberNotFoundException("존재하지 않은 Id 입니다."));

        if(!passwordEncoder.matches(signInDto.getPassword(), findMember.getPassword())) throw new PasswordException("비밀번호가 일치하지 않습니다");

        log.info("로그인 되었습니다. 회원 id = {} , 비밀번호 = {}",signInDto.getLoginId(),signInDto.getPassword());
    }

//    @Transactional
//    public void update(Integer memberId, SignUpDto signUpRequest){
//        Optional<Member> updateMember = memberRepository.findByMemberId(memberId);
//        updateMember.get().updateId(signUpRequest.getLoginId());
//        updateMember.get().updatePassword(signUpRequest.getPassword());
//        memberRepository.save(updateMember.get());
//    }
}
