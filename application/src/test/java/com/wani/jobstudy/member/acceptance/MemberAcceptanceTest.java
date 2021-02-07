package com.wani.jobstudy.member.acceptance;

import com.wani.jobstudy.AcceptanceTest;
import com.wani.jobstudy.member.dto.MemberResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberAcceptanceTest extends AcceptanceTest {
    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";


    /*
     * 회원을 생성한다.
     *
     * 회원의 정보를 조회한다.
     *
     * 회원 정보를 수정한다.
     *
     * 회원 정보를 삭제한다.
     *
     * */

    @Test
    void manageMember() {
        //when
        ExtractableResponse<Response> createResponse = 회원_생성을_요청(EMAIL, PASSWORD, USERNAME);
        //then
        회원_생성됨(createResponse);
        //when
        ExtractableResponse<Response> getResponse = 회원_정보_조회_요청(createResponse);
        //then
        회원_정보_조회됨(getResponse, EMAIL, USERNAME);

        //when
        ExtractableResponse<Response> updateResponse = 회원_정보_수정_요청(createResponse, "update" + EMAIL, "update" + PASSWORD, "update" + USERNAME);
        //then
        회원_정보_수정됨(updateResponse);


    }

    public static ExtractableResponse<Response> 회원_생성을_요청(String email, String password, String username) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("username", username);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when()
                .post("/members")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원_정보_조회_요청(ExtractableResponse<Response> response) {
        String uri = response.header("Location");


        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(uri)
                .then()
                .log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 회원_정보_수정_요청(ExtractableResponse<Response> response, String email, String password, String username) {
        String uri = response.header("Location");

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("username", username);

        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when()
                .put(uri)
                .then()
                .log().all()
                .extract();
    }


    public static void 회원_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }


    public static void 회원_정보_조회됨(ExtractableResponse<Response> response, String email, String username) {
        MemberResponse memberResponse = response.as(MemberResponse.class);
        assertThat(memberResponse.getId()).isNotNull();
        assertThat(memberResponse.getEmail()).isEqualTo(email);
        assertThat(memberResponse.getUsername()).isEqualTo(username);

    }

    public static void 회원_정보_수정됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
