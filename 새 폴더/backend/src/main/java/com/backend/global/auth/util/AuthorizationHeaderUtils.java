package com.backend.global.auth.util;

import com.backend.global.error.ErrorCode;
import com.backend.global.error.exception.AuthenticationException;
import com.backend.global.jwt.constant.GrantType;
import org.springframework.util.StringUtils;

public class AuthorizationHeaderUtils {

    public static void validateAuthorization(String authorizationHeader) {

        // 헤더 체크
        if(!StringUtils.hasText(authorizationHeader)){
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        //Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");
        if(authorizations.length < 2 || (!GrantType.BEARER.getType().equals(authorizations[0]))){
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }
}
