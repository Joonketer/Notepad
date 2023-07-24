package com.backend.global.auth.kakao.service;

import com.backend.global.auth.model.OAuthAttributes;

public interface  SocialLoginApiService {
    OAuthAttributes getUserInfo(String accessToken);
}
