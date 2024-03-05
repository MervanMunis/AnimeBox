package com.WatchList.controller;

import com.WatchList.external.AnimeResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.WatchList.service.IAnimeListService;

@RestController
@AllArgsConstructor
@RequestMapping("/watch-list")
public class WatchListController {
    
    private final IAnimeListService animeListService;
    
    @PostMapping("/{id}")
    public ResponseEntity<String> create(@PathVariable("id") Long animeId) {
        
        String animeItemDto = animeListService.create(animeId);
        
        return new ResponseEntity<>(animeItemDto, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<AnimeResponseDto>> getWatchList() {
        
        List<AnimeResponseDto> animeItemDtos = animeListService.getWatchList();
        
        return ResponseEntity.ok(animeItemDtos);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        
        String animeItemDto = animeListService.delete(id);
        
        return ResponseEntity.ok(animeItemDto);
    }
}

