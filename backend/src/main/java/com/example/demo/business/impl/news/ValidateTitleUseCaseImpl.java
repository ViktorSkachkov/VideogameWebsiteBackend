package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.ValidateTitleUseCase;
import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateTitleUseCaseImpl implements ValidateTitleUseCase {
    private final NewsRepository newsRepository;

    @Override
    public ValidationResponse validateTitle(String title) {
        List<String> titles = newsRepository.findAllTitles();

        if(titles.contains(title)) {
            return ValidationResponse.builder()
                    .confirm(true)
                    .build();
        }
        else {
            return ValidationResponse.builder()
                    .confirm(false)
                    .build();
        }
    }
}
