package com.backend.domain.member.dto;

import com.backend.domain.member.constant.Role;
import com.backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResponseDto {

    private Long memberId;

    private String email;

    private String nickname;

    private String profile_image;

    private String phone_number;

    private String wallet;

    private int stamp_cnt;

    private Role role;


    public static MemberInfoResponseDto of(Member member) {
        return MemberInfoResponseDto.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .profile_image(member.getProfile_image())
                .phone_number(member.getPhone_number())
                .wallet(member.getWallet())
                .stamp_cnt(member.getStamp_cnt())
                .role(member.getRole())
                .build();
    }
}
