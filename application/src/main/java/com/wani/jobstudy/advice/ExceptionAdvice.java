package com.wani.jobstudy.advice;

import com.wani.domain.response.service.ResponseService;
import com.wani.domain.response.vo.CommonResponse;
import com.wani.domain.response.vo.CommonResult;
import com.wani.jobstudy.advice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(CommonResponse.FAIL);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userNotFoundException(HttpServletRequest request , Exception e){
        return responseService.getFailResult(CommonResponse.SIGN_IN_FAIL);
    }

}
