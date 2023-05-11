package com.example.demo.controller;

import com.example.demo.business.cases.videogame.*;
import com.example.demo.configuration.security.isauthenticated.IsAuthenticated;
import com.example.demo.domain.Videogame;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    /**
     *
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("")
    public List<Videogame> getVideogames() {
        return getVideogamesUseCase.getVideogames();
    }

    /**
     *
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/featured")
    public List<Videogame> getFeatured() {
        return getFeaturedVideogamesUseCase.getFeaturedVideogames();
    }

    /**
     *
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/upcoming")
    public List<Videogame> getUpcoming() { return getUpcomingVideogamesUseCase.getUpcomingVideogames(); }

    /**
     *
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE", "ROLE_CUSTOMER"})
    @GetMapping("/{id}")
    public Videogame getVideogame(@PathVariable(value = "id") final int id) {
        return getVideogameUseCase.getVideogame(id);
    }

    /**
     *
     * @param videogame
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PostMapping("")
    public Videogame addVideogame(@RequestBody @Valid Videogame videogame) {
        return addVideogameUseCase.addVideogame(videogame);
    }

    /**
     *
     * @param id
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @DeleteMapping("/{id}")
    public Videogame deleteVideogame(@PathVariable(value = "id") final int id) {
        return deleteVideogameUseCase.deleteVideogame(id);
    }

    /**
     *
     * @param videogame
     * @return
     */
    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PutMapping("")
    public Videogame updateVideogame(@RequestBody @Valid Videogame videogame) {
        return updateVideogameUseCase.updateVideogame(videogame);
    }
}