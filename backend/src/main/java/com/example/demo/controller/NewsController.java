package com.example.demo.controller;

import com.example.demo.business.cases.news.*;
import com.example.demo.domain.News;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final GetNewsUseCase getNewsUseCase;
    private final GetNewsByGameUseCase getNewsByGameUseCase;
    private final GetOneNewsUseCase getOneNewsUseCase;
    private final AddNewsUseCase addNewsUseCase;
    private final DeleteNewsUseCase deleteNewsUseCase;
    private final UpdateNewsUseCase updateNewsUseCase;

    @GetMapping("")
    public List<News> GetNews() {
        return getNewsUseCase.GetNews();
    }
    @GetMapping("/getByGame/{id}")
    public List<News> GetNewsByGame(@PathVariable(value = "id") final int id) {
        return getNewsByGameUseCase.GetNewsByGame(id);
    }
    @GetMapping("/{id}")
    public News GetOneNews(@PathVariable(value = "id") final int id) {
        return getOneNewsUseCase.GetOneNews(id);
    }
    @PostMapping("")
    public News AddNews(@RequestBody @Valid News news) {
        return addNewsUseCase.AddNews(news);
    }
    @DeleteMapping("/{id}")
    public News DeleteNews(@PathVariable(value = "id") final int id) {
        return deleteNewsUseCase.DeleteNews(id);
    }
    @PutMapping("")
    public News UpdateNews(@RequestBody @Valid News news) {
        return updateNewsUseCase.UpdateNews(news);
    }
}
