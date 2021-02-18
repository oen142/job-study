package com.wani.jobstudy.keyword.application;

import com.wani.domain.member.repository.KeywordRepository;
import com.wani.jobstudy.keyword.dto.KeywordRequest;
import com.wani.jobstudy.keyword.dto.KeywordResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public KeywordResponse generateKeyword(KeywordRequest keywordRequest) {
        System.out.println("keywordRequest.getKeywordName() = " + keywordRequest.getKeywordName());
        return KeywordResponse.ofResponse(keywordRepository.save(keywordRequest.toKeyword()));
    }

    public List<KeywordResponse> findAllKeywords() {
        return keywordRepository.findAll()
                .stream()
                .map(KeywordResponse::ofResponse)
                .collect(Collectors.toList());
    }

    public KeywordResponse enrollMemberToKeyword(List<KeywordRequest> keywordRequests) {

        return null;
    }

}
