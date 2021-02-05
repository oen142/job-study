package com.wani.domain.response.vo;

public enum CommonResponse {

    SUCCESS(1, "성공"),
    FAIL(0, "에러가 발생했습니다."),
    NOT_AUTHENTICATE(-1, "인증에 실패했습니다.");

    int code;
    String msg;

    CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
