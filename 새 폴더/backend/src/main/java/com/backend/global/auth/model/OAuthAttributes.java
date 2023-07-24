package com.backend.global.auth.model;

import com.backend.domain.member.constant.MemberType;
import com.backend.domain.member.constant.Role;
import com.backend.domain.member.constant.SocialType;
import com.backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class OAuthAttributes {  // 회원 정보 통일

    private String name;
    private String email;
    private String profile;
    private SocialType socialType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .nickname(name)
                .email(email)
                .socialType(socialType)
                .profile_image(profile)
                .role(role)
                .build();
    }
}
