package com.backend.domain.member.service;

import com.backend.domain.member.dto.MemberSignDto;
import com.backend.domain.member.entity.Member;
import com.backend.domain.member.repository.MemberRepository;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.exception.AuthenticationException;
import com.backend.global.error.exception.BusinessException;
import com.backend.global.error.exception.EntityNotFoundException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private final MemberRepository memberRepository;

    public Member registerMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 중복 검증
    private void validateDuplicateMember(Member member) throws BusinessException {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()){
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_USER);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findMemberByRefreshToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        Date expiryDate = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(refreshToken).getBody().getExpiration();
        if(expiryDate.before(new Date())) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
        return member;
    }

    public Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }
}
