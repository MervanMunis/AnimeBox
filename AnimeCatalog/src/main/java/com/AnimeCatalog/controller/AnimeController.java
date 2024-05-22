package com.AnimeCatalog.controller;

import com.AnimeCatalog.dto.AnimeDto;
import com.AnimeCatalog.exception.EmptyFileException;
import com.AnimeCatalog.service.AnimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/anime")
public class AnimeController {


    private final AnimeService animeService;

    public AnimeController(AnimeService animeService){
        this.animeService = animeService;
    }

    @PostMapping("/add")
    public ResponseEntity<AnimeDto> createAnime(@RequestPart MultipartFile file,
                                                @RequestPart String animeDtoObj) throws EmptyFileException,
                                                                                            IOException {
        if(file.isEmpty()){
            throw new EmptyFileException("File is empty! Please send another file!");
        }
        AnimeDto animeDto = convertToAnimeDto(animeDtoObj);

        AnimeDto dto = animeService.createAnime(animeDto, file);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/category")
    public ResponseEntity<List<AnimeDto>> getAllAnimeByCategory(@RequestBody List<String> categories) {
        Set<String> categorySet = new HashSet<>(categories);
        List<AnimeDto> animeList = animeService.getAllAnimeByCategory(categorySet);
        return ResponseEntity.ok(animeList);
    }

    @GetMapping("/{animeId}")
    public ResponseEntity<AnimeDto> getAnimeById(@PathVariable("animeId") Integer animeId) {
        AnimeDto animeDto = animeService.getAnimeById(animeId);
        return ResponseEntity.ok(animeDto);
    }

    @GetMapping("/allAnimeList")
    public ResponseEntity<List<AnimeDto>> getAllAnime() {
        List<AnimeDto> animeList = animeService.getAllAnime();
        return ResponseEntity.ok(animeList);
    }

    @PutMapping("/update/{animeId}")
    public ResponseEntity<AnimeDto> updateAnime(@PathVariable("animeId") Integer animeId,
                                                        @RequestPart MultipartFile file,
                                                        @RequestPart String animeDtoObj) throws IOException{
        if (file.isEmpty()) {
            file = null;
        }
        AnimeDto animeDto = convertToAnimeDto(animeDtoObj);
        AnimeDto  animeResponseDto = animeService.updateAnime(animeId, animeDto, file);
        return ResponseEntity.ok(animeResponseDto);
    }

    @DeleteMapping("/delete/{animeId}")
    public ResponseEntity<String> deleteAnime(@PathVariable("animeId") Integer animeId) throws IOException {
        String responseMessage = animeService.deleteAnime(animeId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    private AnimeDto convertToAnimeDto(String animeDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(animeDtoObj, AnimeDto.class);
    }
}
