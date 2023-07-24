package com.backend.global.auth.kakao.service;

import com.backend.domain.member.constant.MemberType;
import com.backend.domain.member.constant.SocialType;
import com.backend.global.auth.kakao.client.KakaoUserInfoClient;
import com.backend.global.auth.kakao.dto.KakaoUserInfoResponseDto;
import com.backend.global.auth.model.OAuthAttributes;
import com.backend.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService{

    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf8";

    @Override
    public OAuthAttributes getUserInfo(String  accessToken) {   // 받은 엑세스토큰
        KakaoUserInfoResponseDto kakaoUserInfoResponseDto
                = kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE, GrantType.BEARER.getType()+ " " + accessToken);

        KakaoUserInfoResponseDto.KakaoAccount kakaoAccount = kakaoUserInfoResponseDto.getKakaoAccount();
        String email = kakaoAccount.getEmail();

        return OAuthAttributes.builder()
                .email(!StringUtils.hasText(email) ? kakaoUserInfoResponseDto.getId() : email)
                .name(kakaoAccount.getProfile().getNickname())
                .profile(kakaoAccount.getProfile().getThumbnailImageUrl())
                .socialType(SocialType.KAKAO)
                .build();
    }

}
