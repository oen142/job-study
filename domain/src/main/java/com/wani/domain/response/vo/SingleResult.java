package com.wani.domain.response.vo;


import lombok.Getter;

@Getter
public class SingleResult<T> extends CommonResult {

    private T data;

    public SingleResult(int code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }
}
