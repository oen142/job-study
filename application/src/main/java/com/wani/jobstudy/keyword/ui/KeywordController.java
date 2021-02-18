package com.wani.jobstudy.keyword.ui;

import com.wani.domain.response.service.ResponseService;
import com.wani.domain.response.vo.ListResult;
import com.wani.jobstudy.keyword.application.KeywordService;
import com.wani.jobstudy.keyword.dto.KeywordRequest;
import com.wani.jobstudy.keyword.dto.KeywordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KeywordController {

    private final KeywordService keywordService;
    private final ResponseService responseService;

    public KeywordController(KeywordService keywordService, ResponseService responseService) {
        this.keywordService = keywordService;
        this.responseService = responseService;
    }

    @PostMapping("/keywords")
    public ResponseEntity generateKeyword(@RequestBody KeywordRequest keywordRequest) {

        KeywordResponse response = keywordService.generateKeyword(keywordRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/keywords")
    public ResponseEntity<ListResult<KeywordResponse>> findAllKeywords() {
        return ResponseEntity.ok()
                .body(
                        responseService.getSuccessListResult(keywordService.findAllKeywords()));
    }

}
