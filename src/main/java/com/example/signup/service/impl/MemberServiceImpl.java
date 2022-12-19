package com.example.signup.service.impl;

import com.example.signup.dto.request.MemberSignInRequestDto;
import com.example.signup.dto.request.MemberSignUpRequestDto;
import com.example.signup.dto.response.TokenResponseDto;
import com.example.signup.entity.Member;
import com.example.signup.exception.MemberExistException;
import com.example.signup.exception.MemberNotFoundException;
import com.example.signup.exception.PasswordNotMatch;
import com.example.signup.repository.MemberRepository;
import com.example.signup.security.jwt.JwtTokenProvider;
import com.example.signup.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    @Override
    public Integer signUp (MemberSignUpRequestDto signUpDto) {
        if (memberRepository.findByLoginId(signUpDto.getLoginId()).isPresent()){
            throw new MemberExistException("이미 존재하는 id 입니다.");
        }

        Member member = memberRepository.save(signUpDto.toEntity());
        member.addUserAuthority();
        member.passwordEncode(passwordEncoder);

        return member.getMemberId();
    }

    @Transactional
    @Override
    public TokenResponseDto login(MemberSignInRequestDto signInDto) {
        Member member = memberRepository.findByLoginId(signInDto.getLoginId())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않은 Id 입니다"));

        validateMatchedPassword(signInDto.getPassword(), member.getPassword());

        String accessToken = jwtTokenProvider.createAccessToken(member.getLoginId(), String.valueOf(member.getRole()));
        String refreshToken = jwtTokenProvider.createRefreshToken();

        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    @Override
    public TokenResponseDto issueAccessToken(HttpServletRequest request) {

        //TODO : 만료된 accessToken 과 refreshToken 을 가져옴
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        //TODO : accessToken 이 만료되었으면
        if(jwtTokenProvider.validateAccessToken(accessToken)) {
            log.info("access 토큰 만료됨");
            //TODO : 만약 refreshToken 이 유효하다면
            if(jwtTokenProvider.validateRefreshToken(refreshToken)) {
                log.info("refresh Token 은 유효합니다.");

                //TODO : DB에 저장해두었던 refreshToken 을 불러오고 새로운 Access Token 을 생성하기 위함
                Member member = memberRepository.findByLoginId(jwtTokenProvider.getUserLoginId(refreshToken))
                        .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 Id 입니다."));

                //TODO : 만약 DB refreshToken 와 요청한 refreshToken 가 같다면
                if(refreshToken.equals(member.getRefreshToken())) {
                    //TODO : 새로운 accessToken 생성
                    accessToken = jwtTokenProvider.createAccessToken(member.getLoginId(), member.getRole().name());
                }
                else {
                    log.info("토큰이 변조되었습니다.");
                }
            }
            else {
                log.info("Refresh Token 이 유효하지 않습니다.");
            }
        }
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if (!passwordEncoder.matches(validPassword, memberPassword)) {
            throw new PasswordNotMatch("비밀번호가 일치하지 않습니다");
        }
    }

}
