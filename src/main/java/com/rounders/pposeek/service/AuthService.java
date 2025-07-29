package com.rounders.pposeek.service;

import com.rounders.pposeek.dto.LoginRequest;
import com.rounders.pposeek.dto.LoginResponse;
import com.rounders.pposeek.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {
    
    public LoginResponse login(LoginRequest request) {
        // 실제 구현에서는 DB 조회 및 비밀번호 검증
        UserDto user = UserDto.builder()
                .id(1L)
                .email(request.getEmail())
                .name("홍길동")
                .phone("010-1234-5678")
                .role("user")
                .build();
                
        return LoginResponse.builder()
                .token("Bearer " + UUID.randomUUID().toString())
                .user(user)
                .build();
    }
    
    public UserDto signup(UserDto userDto) {
        // 실제 구현에서는 DB 저장
        userDto.setId(System.currentTimeMillis());
        return userDto;
    }
    
    public void logout(String token) {
        // 토큰 무효화 로직
    }
    
    public UserDto getCurrentUser(String token) {
        // 토큰으로 사용자 조회
        return UserDto.builder()
                .id(1L)
                .email("user@example.com")
                .name("홍길동")
                .phone("010-1234-5678")
                .role("user")
                .build();
    }
}
