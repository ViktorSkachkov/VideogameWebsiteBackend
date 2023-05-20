package com.example.demo.controller;

import com.example.demo.business.cases.review.AddReviewUseCase;
import com.example.demo.business.cases.review.DeleteReviewUseCase;
import com.example.demo.business.cases.review.GetReviewsByItemUseCase;
import com.example.demo.business.cases.review.UpdateReviewUseCase;
import com.example.demo.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddReviewUseCase addReviewUseCase;
    @MockBean
    private DeleteReviewUseCase deleteReviewUseCase;
    @MockBean
    private GetReviewsByItemUseCase getReviewsByItemUseCase;
    @MockBean
    private UpdateReviewUseCase updateReviewUseCase;


    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void deleteReview() throws Exception {
        Review review = Review.builder()
                .id(1L)
                .text("text")
                .reviewedItemId(24)
                .userId(41)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .typeOfReviewedItem("game")
                .build();
        when(deleteReviewUseCase.deleteReview(1))
                .thenReturn(review);
        mockMvc.perform(delete("/reviews/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                                                     {"id":1, "text":"text", "reviewed_item_id":24,"user_id":41,"time":"2017-12-13T15:56:30","type_of_reviewed_item":"game"}
                        """));
        verify(deleteReviewUseCase).deleteReview(1);
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void getReviewsByItem() throws Exception {
        Review review1 = Review.builder()
                .id(1L)
                .text("text")
                .reviewedItemId(24)
                .userId(41)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .typeOfReviewedItem("game")
                .build();
        Review review2 = Review.builder()
                .id(2L)
                .text("text")
                .reviewedItemId(24)
                .userId(41)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .typeOfReviewedItem("game")
                .build();
        when(getReviewsByItemUseCase.getReviewsByItem(24, "game"))
                .thenReturn(List.of(review1, review2));
        mockMvc.perform(get("/reviews/24/game"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                          [                        {"id":1, "text":"text", "reviewed_item_id":24,"user_id":41,"time":"2017-12-13T15:56:30","type_of_reviewed_item":"game"},
                                                  {"id":2, "text":"text", "reviewed_item_id":24,"user_id":41,"time":"2017-12-13T15:56:30","type_of_reviewed_item":"game"}]
                        """));
        verify(getReviewsByItemUseCase).getReviewsByItem(24, "game");
    }

    @Test
    @WithMockUser(username = "username1", password = "password", roles = {"CUSTOMER"})
    void updateReview() throws Exception {
        Review review = Review.builder()
                .id(1L)
                .text("text")
                .reviewedItemId(24)
                .userId(41)
                .time(LocalDateTime.of(2017, 12, 13, 15, 56, 30))
                .typeOfReviewedItem("game")
                .build();
        when(updateReviewUseCase.updateReview(review))
                .thenReturn(review);
        mockMvc.perform(put("/reviews")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                                {"id":1, "text":"text", "reviewed_item_id":24,"user_id":41,"time":"2017-12-13T15:56:30","type_of_reviewed_item":"game"}
                                """)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("""
                         {"id":1, "text":"text", "reviewed_item_id":24,"user_id":41,"time":"2017-12-13T15:56:30","type_of_reviewed_item":"game"}
                        """));
        verify(updateReviewUseCase).updateReview(review);
    }
}