package com.wani.domain.response.service;

import com.wani.domain.response.vo.ListResult;
import com.wani.domain.response.vo.SingleResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ResponseServiceTest {

    @Autowired
    private ResponseService responseService;


    @Test
    @DisplayName("single result 테스트")
    void testSuccessSingleResult() {
        TestData testData = new TestData("emily", 22);
        SingleResult<TestData> singleTestData = responseService.getSuccessSingleResult(testData);

        assertThat(singleTestData.getData()).isEqualTo(testData);
        assertThat(singleTestData.getCode()).isEqualTo(1);
        assertThat(singleTestData.getMsg()).isEqualTo("성공");
    }


    @Test
    @DisplayName("list result 테스트")
    void testSuccessListResult() {

        List<TestData> testDataList = Arrays.asList(new TestData("emily", 22), new TestData("kims", 23));
        ListResult<TestData> testDataListResult = responseService.getSuccessListResult(testDataList);

        assertThat(testDataListResult.getData()).isEqualTo(testDataList);
        assertThat(testDataListResult.getCode()).isEqualTo(1);
        assertThat(testDataListResult.getMsg()).isEqualTo("성공");
    }

}