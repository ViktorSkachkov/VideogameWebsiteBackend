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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddGameOrderUseCaseImplTest {
    @Mock
    private GameOrderRepository gameOrderRepository;
    @Mock
    private VideogameRepository videogameRepository;
    @InjectMocks
    private AddGameOrderUseCaseImpl addGameOrderUseCase;

    @Test
    void addGameOrder() {
        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = time.format(dateTimeFormatter);

        GameOrder expectedResult = GameOrder.builder()
                .id(1)
                .game(23)
                .user(41)
                .units(2)
                .approved(false)
                .time(time)
                .totalPrice(48)
                .dateFormatted(formattedDateTime)
                .build();
        GameOrderPersistence gameOrder = GameOrderPersistence.builder()
                .game(23)
                .user(41)
                .units(2)
                .approved(false)
                .time(time)
                .totalPrice(48)
                .build();
        when(videogameRepository.findPriceById((long) gameOrder.getGame()))
                .thenReturn(gameOrder.getTotalPrice());
        when(gameOrderRepository.save(gameOrder))
                .thenReturn(gameOrder);
        GameOrder actualResult = addGameOrderUseCase.addGameOrder(expectedResult);
        assertEquals(expectedResult, actualResult);
        verify(videogameRepository).findPriceById((long) gameOrder.getGame());
        verify(gameOrderRepository).save(gameOrder);
    }
}