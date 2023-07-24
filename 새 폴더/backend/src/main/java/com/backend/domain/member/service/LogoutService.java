package com.backend.domain.member.service;



import com.backend.domain.member.entity.Member;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.exception.AuthenticationException;
import com.backend.global.jwt.constant.TokenType;
import com.backend.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutService {
    private final MemberService memberService;
    private final TokenManager tokenManager;

    public void logout(String accessToken) {
        // 검증
        tokenManager.validateToken(accessToken);

        // 타입 확인
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String tokenType = tokenClaims.getSubject();

        if(!TokenType.isAccessToken(tokenType)) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        // 리프레쉬 토큰 만료 처리
        Long memberId  = Long.valueOf((Integer)tokenClaims.get("memberId"));
        Member member =  memberService.findMemberByMemberId(memberId);
        member.expireRefreshToken(LocalDateTime.now());
    }
}
