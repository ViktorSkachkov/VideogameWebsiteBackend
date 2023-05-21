package com.example.demo.business.impl.gameorder;

import com.example.demo.domain.GameOrder;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGameCartItemsUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private GetGameCartItemsUseCaseImpl getGameCartItemsUseCase;

    @Test
    void getGameCartItemsUseCase() {
        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = time.format(dateTimeFormatter);

        GameOrder additionOrder1 = GameOrder.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .totalPrice(0)
                .approved(false)
                .time(time)
                .dateFormatted(formattedDateTime)
                .build();

        GameOrderPersistence gameOrderPersistence1 = GameOrderPersistence.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .approved(false)
                .time(time)
                .build();
        GameOrderPersistence gameOrderPersistence2 = GameOrderPersistence.builder()
                .id(2)
                .game(23)
                .user(20)
                .units(2)
                .approved(false)
                .time(time)
                .build();
        when(gameOrderRepository.findAll())
                .thenReturn(List.of(gameOrderPersistence1, gameOrderPersistence2));
        List<GameOrder> actualResult = getGameCartItemsUseCase.getGameCartItems(41);
        List<GameOrder> expectedResult = new ArrayList<>();
        expectedResult.add(additionOrder1);
        assertEquals(expectedResult, actualResult);
        verify(gameOrderRepository).findAll();
    }
}