package com.example.demo.controller;

import com.example.demo.business.cases.reviews.AddReviewUseCase;
import com.example.demo.business.cases.reviews.DeleteReviewUseCase;
import com.example.demo.business.cases.reviews.GetReviewsByItemUseCase;
import com.example.demo.business.cases.reviews.UpdateReviewUseCase;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.Review;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final AddReviewUseCase addReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;
    private final GetReviewsByItemUseCase getReviewsByItemUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PostMapping("")
    public Review AddReview(@RequestBody @Valid Review review) {
        review.setTime(LocalDateTime.now());
        return addReviewUseCase.AddReview(review);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @DeleteMapping("{id}")
    public Review DeleteReview(@PathVariable(value = "id") final int id) {
        return deleteReviewUseCase.DeleteReview(id);
    }

    /*@IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("")
    public List<Review> GetReviewsByItem(@RequestBody @Valid ReviewRequest reviewRequest) {
        return getReviewsByItemUseCase.GetReviewsByItem(reviewRequest);
    }*/

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{itemId}/{type}")
    public List<Review> GetReviewsByItem(@PathVariable(value = "itemId") final int itemId, @PathVariable(value = "type") final String type) {
        return getReviewsByItemUseCase.GetReviewsByItem(itemId, type);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @PutMapping("")
    public Review UpdateReview(@RequestBody @Valid Review review) {
        return updateReviewUseCase.UpdateReview(review);
    }


}
