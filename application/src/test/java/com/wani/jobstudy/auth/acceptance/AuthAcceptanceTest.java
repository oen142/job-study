package com.wani.jobstudy.auth.acceptance;

import com.wani.jobstudy.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.wani.jobstudy.member.acceptance.MemberAcceptanceTest.회원_생성을_요청;

public class AuthAcceptanceTest extends AcceptanceTest {
    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";



    public static ExtractableResponse<Response> 회원_등록이되어_있다(String email , String password, String username){
        return 회원_생성을_요청(email , password , username);
    }

}
