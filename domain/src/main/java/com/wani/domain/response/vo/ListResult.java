package com.wani.domain.response.vo;

import lombok.Getter;

import java.util.List;


@Getter
public class ListResult<T> extends CommonResult {

    private List<T> data;

    public ListResult(int code, String msg, List<T> data) {
        super(code, msg);
        this.data = data;
    }
}
