package com.backend.domain.validator;

import com.backend.domain.member.constant.MemberType;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.exception.BusinessException;

public class OauthValidator {

    public void validateMemberType(String memberType) {
        if(!MemberType.isMemberType(memberType)){
            throw new BusinessException(ErrorCode.INVALID_USER_TYPE);
        }
    }
}
