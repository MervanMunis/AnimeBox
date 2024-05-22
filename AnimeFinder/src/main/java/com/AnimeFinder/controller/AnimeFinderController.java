package com.AnimeFinder.controller;

import com.AnimeFinder.external.dto.AnimeDto;
import com.AnimeFinder.dto.UserInput;
import com.AnimeFinder.service.AnimeFinderService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anime-finder-bot")
public class AnimeFinderController {

    private final AnimeFinderService animeFinder;

    public AnimeFinderController(AnimeFinderService animeFinder){
        this.animeFinder = animeFinder;
    }

    @PostMapping
    public ResponseEntity<List<AnimeDto>> animeFinderBot(@RequestBody UserInput userInput) {

        List<AnimeDto> response = animeFinder.animeFinderBot(userInput);
        return ResponseEntity.ok(response);
    }
}
