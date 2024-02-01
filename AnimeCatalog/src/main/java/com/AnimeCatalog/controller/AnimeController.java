package com.AnimeCatalog.controller;

import com.AnimeCatalog.dto.AnimeRequestDto;
import com.AnimeCatalog.dto.AnimeResponseDto;
import com.AnimeCatalog.service.IAnimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/anime")
public class AnimeController {

    private final IAnimeService animeService;

    @PostMapping
    public ResponseEntity<AnimeResponseDto> createAnime(@RequestBody AnimeRequestDto animeRequestDto) {
        AnimeResponseDto animeResponseDto = animeService.createAnime(animeRequestDto);
        return new ResponseEntity<>(animeResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> getAnimeById(@PathVariable("id") Long animeId) {
        AnimeResponseDto animeResponseDto = animeService.getAnimeById(animeId);
        return ResponseEntity.ok(animeResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<AnimeResponseDto>>  getAllAnime() {
        List<AnimeResponseDto> animeList = animeService.getAllAnime();
        return ResponseEntity.ok(animeList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<AnimeResponseDto>> getAllAnimeByCategory(String category) {
        List<AnimeResponseDto> animeList = animeService.getAllAnimeByCategory(category);
        return ResponseEntity.ok(animeList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> updateAnime(@PathVariable("id") Long animeId,
                                                        AnimeRequestDto animeRequestDto) {
        AnimeResponseDto  animeResponseDto = animeService.updateAnime(animeId, animeRequestDto);

        return ResponseEntity.ok(animeResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnime(@PathVariable("id") Long animeId) {
        String responseMessage = animeService.deleteAnime(animeId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
