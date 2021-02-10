package com.wani.jobstudy.integration;

import com.wani.jobstudy.config.jwt.JWTUtil;
import com.wani.jobstudy.config.jwt.UserLogin;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public class RefreshTokenTest extends IntegrationTest {

    protected Tokens getToken(String username, String password) throws URISyntaxException {
        UserLogin login = UserLogin.builder().type(UserLogin.Type.login).username(username).password(password).build();
        HttpEntity<UserLogin> body = new HttpEntity<>(login);
        ResponseEntity<String> response = restTemplate.exchange(uri("/login"),
                HttpMethod.POST, body, String.class);
        return new Tokens(getAccessToken(response), getRefreshToken(response));
    }

    private Tokens getRefreshToken(String refreshToken) {
        return null;
    }

    protected String getRefreshToken(ResponseEntity<String> response) {
        return response.getHeaders().get(JWTUtil.REFRESH_HEADER).get(0);
    }
}
