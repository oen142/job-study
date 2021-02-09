package com.wani.jobstudy.auth.acceptance;

import com.wani.jobstudy.AcceptanceTest;
import com.wani.jobstudy.auth.dto.TokenResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static com.wani.jobstudy.member.acceptance.MemberAcceptanceTest.회원_생성을_요청;
import static com.wani.jobstudy.member.acceptance.MemberAcceptanceTest.회원_정보_조회됨;

public class AuthAcceptanceTest extends AcceptanceTest {
    private static final String EMAIL = "email@email.com";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Test
    @DisplayName("Bearer Auth를 테스트 한다.")
    void myInfoWithBearerAuth() {
        회원_등록되어_있음(EMAIL, PASSWORD, USERNAME);

        TokenResponse tokenResponse = 로그인_되어_있음(EMAIL, PASSWORD);

        ExtractableResponse<Response> response = 내_회원_정보_조회_요청(tokenResponse);

        회원_정보_조회됨(response, EMAIL, USERNAME);
    }

    public static ExtractableResponse<Response> 회원_등록되어_있음(String email, String password, String username) {
        return 회원_생성을_요청(email, password, username);
    }

    public static TokenResponse 로그인_되어_있음(String email, String password) {
        ExtractableResponse<Response> response = 로그인_요청(email, password);
        return response.as(TokenResponse.class);
    }

    private static ExtractableResponse<Response> 로그인_요청(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when()
                .post("/login/token")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    public static ExtractableResponse<Response> 내_회원_정보_조회_요청(TokenResponse tokenResponse) {
        return RestAssured.given().log().all()
                .auth().oauth2(tokenResponse.getAccessToken())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/members/me")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }


}
