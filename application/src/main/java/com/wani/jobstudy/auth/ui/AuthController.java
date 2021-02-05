package com.wani.jobstudy.auth.ui;

import com.wani.jobstudy.auth.application.AuthService;
import com.wani.jobstudy.auth.dto.TokenRequest;
import com.wani.jobstudy.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login/token")
    public ResponseEntity<TokenResponse> login(@RequestBody TokenRequest request) {
        TokenResponse token = authService.login(request);
        return ResponseEntity.ok().body(token);

    }
}