package com.wani.domain.response.service;


import com.wani.domain.response.vo.CommonResponse;
import com.wani.domain.response.vo.CommonResult;
import com.wani.domain.response.vo.ListResult;
import com.wani.domain.response.vo.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {


    // 단일건 결과를 처리하는 메서드
    public <T> SingleResult<T> getSuccessSingleResult(T data) {
        return new SingleResult<>(CommonResponse.SUCCESS.getCode(), CommonResponse.SUCCESS.getMsg(), data);
    }

    // 다수건의 결과를 처리하는 메서드
    public <T> ListResult<T> getSuccessListResult(List<T> data) {
        return new ListResult<>(CommonResponse.SUCCESS.getCode(), CommonResponse.SUCCESS.getMsg(), data);
    }


    // 실패를 처리하는 메서드
    public CommonResult getFailResult(CommonResponse commonResponse) {
        return new CommonResult(commonResponse.getCode(), commonResponse.getMsg());
    }


}