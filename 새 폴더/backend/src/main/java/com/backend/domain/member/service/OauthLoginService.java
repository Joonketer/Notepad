package com.backend.domain.member.service;


import com.backend.domain.member.constant.MemberType;
import com.backend.domain.member.constant.Role;
import com.backend.domain.member.dto.OauthLoginDto;
import com.backend.domain.member.entity.Member;
import com.backend.global.auth.kakao.service.SocialLoginApiService;
import com.backend.global.auth.kakao.service.SocialLoginApiServiceFactory;
import com.backend.global.auth.model.OAuthAttributes;
import com.backend.global.jwt.dto.JwtTokenDto;
import com.backend.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public OauthLoginDto.Response oauthLogin(String accessToken, MemberType memberType) {
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
        log.info("userInfo : {}",  userInfo);

        JwtTokenDto jwtTokenDto;
        Optional<Member> optionalMember = memberService.findMemberByEmail(userInfo.getEmail());
        Member oauthMember;
        if(optionalMember.isEmpty()) {  //신규가입
            oauthMember = userInfo.toMemberEntity(memberType, Role.ADMIN);
            oauthMember = memberService.registerMember(oauthMember);
        }else{  // 기존 멤버
            oauthMember = optionalMember.get();
        }
        // 토큰 생성
        jwtTokenDto = tokenManager.createJwtTokenDto(oauthMember.getMemberId(), oauthMember.getRole());
        oauthMember.updateRefreshToken(jwtTokenDto);

        return OauthLoginDto.Response.of(jwtTokenDto);
    }
}
