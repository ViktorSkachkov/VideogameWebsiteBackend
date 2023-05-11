package com.example.demo.business.impl.videogame;

import com.example.demo.persistence.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUpcomingVideogamesUseCaseImplTest {
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private GetUpcomingVideogamesUseCaseImpl getUpcomingVideogamesUseCase;

    @Test
    void getUpcomingVideogames() {

    }
}