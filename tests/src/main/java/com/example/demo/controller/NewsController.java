package com.example.demo.controller;

import com.example.demo.business.cases.news.*;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.News;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3090/", "http://localhost:3000/"}, allowedHeaders = "*")
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final GetNewsUseCase getNewsUseCase;
    private final GetNewsByGameUseCase getNewsByGameUseCase;
    private final GetOneNewsUseCase getOneNewsUseCase;
    private final AddNewsUseCase addNewsUseCase;
    private final DeleteNewsUseCase deleteNewsUseCase;
    private final UpdateNewsUseCase updateNewsUseCase;
    private final ValidateTitleUseCase validateTitleUseCase;

    /**
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("")
    public List<News> getNews() {
        return getNewsUseCase.getNews();
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/getByGame/{id}")
    public List<News> getNewsByGame(@PathVariable(value = "id") final int id) {
        return getNewsByGameUseCase.getNewsByGame(id);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public News getOneNews(@PathVariable(value = "id") final int id) {
        return getOneNewsUseCase.getOneNews(id);
    }

    /**
     * @param news
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PostMapping("")
    public News addNews(@RequestBody @Valid News news) {
        return addNewsUseCase.addNews(news);
    }

    /**
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @DeleteMapping("{id}")
    public News deleteNews(@PathVariable(value = "id") final int id) {
        return deleteNewsUseCase.deleteNews(id);
    }

    /**
     * @param news
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PutMapping("")
    public News updateNews(@RequestBody @Valid News news) {
        return updateNewsUseCase.updateNews(news);
    }

    /**
     * @param title
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @GetMapping("/validate/{title}")
    public boolean validateName(@PathVariable(value = "title") final String title) {
        return validateTitleUseCase.validateTitle(title);
    }
}
