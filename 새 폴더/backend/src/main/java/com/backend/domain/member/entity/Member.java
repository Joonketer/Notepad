package com.backend.domain.member.entity;

import com.backend.domain.common.BaseEntity;
import com.backend.domain.member.constant.Role;
import com.backend.domain.member.constant.SocialType;
import com.backend.global.auth.util.DateTimeUtils;
import com.backend.global.jwt.dto.JwtTokenDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    private String password;

    private String nickname;

    private String profile_image;

    private String phone_number;

    private String wallet;

    private int stamp_cnt;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(length = 250)
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    private LocalDateTime tokenExpirationTime;

    //== 멤버 필드 업데이트 ==//
    public void updateNickname(String updateNickname) {
        this.nickname = updateNickname;
    }

    public void updateProfileImage(String profile_image){
        this.profile_image = profile_image;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }


    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }


}
