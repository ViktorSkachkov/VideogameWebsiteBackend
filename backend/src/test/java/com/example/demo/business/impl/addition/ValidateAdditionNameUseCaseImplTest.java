package com.example.demo.business.impl.addition;

import com.example.demo.persistence.repository.AdditionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAdditionNameUseCaseImplTest {
    @Mock
    private AdditionRepository additionRepository;
    @InjectMocks
    private ValidateAdditionNameUseCaseImpl validateAdditionNameUseCase;

    @Test
    void validateAdditionName() {
        when(additionRepository.findAllNames(false))
                .thenReturn(List.of("Name"));
        boolean actualResult = validateAdditionNameUseCase.validateAdditionName("Name");
        assertEquals(true, actualResult);
    }
}