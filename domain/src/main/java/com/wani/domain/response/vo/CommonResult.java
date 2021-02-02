package com.wani.domain.response.vo;

import lombok.Getter;

@Getter
public class CommonResult {

    private final int code;
    private final String msg;


    public CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
