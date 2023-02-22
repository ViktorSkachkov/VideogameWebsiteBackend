package com.example.demo.controller;

import com.example.demo.business.cases.videogames.*;
import com.example.demo.domain.Videogame;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000/", allowedHeaders = "*")
@RequestMapping("/videogames")
@RequiredArgsConstructor
public class VideogameController {
    private final GetVideogamesUseCase getVideogamesUseCase;
    private final GetVideogameUseCase getVideogameUseCase;
    private final AddVideogameUseCase addVideogameUseCase;
    private final UpdateVideogameUseCase updateVideogameUseCase;
    private final DeleteVideogameUseCase deleteVideogameUseCase;
    private final GetFeaturedVideogamesUseCase getFeaturedVideogamesUseCase;
    private final GetUpcomingVideogamesUseCase getUpcomingVideogamesUseCase;


    @GetMapping("")
    public List<Videogame> GetVideogames() {
        return getVideogamesUseCase.GetVideogames();
    }
    @GetMapping("/featured")
    public List<Videogame> GetFeatured() {
        return getFeaturedVideogamesUseCase.GetFeaturedVideogames();
    }
    @GetMapping("/upcoming")
    public List<Videogame> GetUpcoming() { return getUpcomingVideogamesUseCase.GetUpcomingVideogames(); }
    @GetMapping("/{id}")
    public Videogame GetVideogame(@PathVariable(value = "id") final int id) {
        return getVideogameUseCase.GetVideogame(id);
    }
    @PostMapping("")
    public Videogame AddVideogame(@RequestBody @Valid Videogame videogame) {
        return addVideogameUseCase.AddVideogame(videogame);
    }
    @DeleteMapping("/{id}")
    public Videogame DeleteVideogame(@PathVariable(value = "id") final int id) {
        return deleteVideogameUseCase.DeleteVideogame(id);
    }
    @PutMapping("")
    public Videogame UpdateVideogame(@RequestBody @Valid Videogame videogame) {
        return updateVideogameUseCase.UpdateVideogame(videogame);
    }
}