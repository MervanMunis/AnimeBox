package com.AnimeFinder.controller;

import com.AnimeFinder.external.dto.AnimeResponseDto;
import com.AnimeFinder.external.dto.UserInput;
import com.AnimeFinder.service.IAnimeFinderService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/anime-finder-bot")
public class AnimeFinderController {
    
    private final IAnimeFinderService animeFinder;
    
    @PostMapping
    public ResponseEntity<List<AnimeResponseDto>> animeFinderBot(@RequestBody UserInput message) {
        
        List<AnimeResponseDto> response = animeFinder.animeFinderBot(message.getUser_input());
        return ResponseEntity.ok(response);
    }
}
