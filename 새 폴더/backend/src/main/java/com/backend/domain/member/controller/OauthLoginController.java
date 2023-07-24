package com.backend.domain.member.controller;

import com.backend.domain.member.constant.MemberType;
import com.backend.domain.member.dto.OauthLoginDto;
import com.backend.domain.member.service.OauthLoginService;
import com.backend.domain.validator.OauthValidator;
import com.backend.global.auth.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthLoginController {

    private final OauthValidator oauthValidator;
    private final OauthLoginService oauthLoginService;

    @PostMapping("/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto,
                                                             HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
        oauthValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

        String accessToken = authorizationHeader.split(" ")[1]; // 카카오에서 받은 엑세스 토큰
        OauthLoginDto.Response jwtTokenResponseDto = oauthLoginService.oauthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));
        return ResponseEntity.ok(jwtTokenResponseDto);
    }

}
