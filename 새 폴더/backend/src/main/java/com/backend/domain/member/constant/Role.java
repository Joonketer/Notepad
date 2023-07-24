package com.backend.domain.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER, ADMIN, GUEST;

    public static Role from(String role) {
        return Role.valueOf(role);
    }

}
