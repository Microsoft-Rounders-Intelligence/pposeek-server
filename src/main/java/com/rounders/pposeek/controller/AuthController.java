package com.rounders.pposeek.controller;

import com.rounders.pposeek.dto.LoginRequest;
import com.rounders.pposeek.dto.LoginResponse;
import com.rounders.pposeek.dto.UserDto;
import com.rounders.pposeek.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
// 싱크 커밋
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.signup(userDto));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authService.getCurrentUser(token));
    }
}
