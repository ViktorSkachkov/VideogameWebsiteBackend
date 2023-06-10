package com.example.demo.business.impl.news;

import com.example.demo.business.cases.news.ValidateTitleUseCase;
import com.example.demo.persistence.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateTitleUseCaseImpl implements ValidateTitleUseCase {
    private final NewsRepository newsRepository;

    @Override
    public boolean validateTitle(String title) {
        List<String> titles = newsRepository.findAllTitles();

        if(titles.contains(title)) {
            return true;
        }
        else {
            return false;
        }
    }
}
