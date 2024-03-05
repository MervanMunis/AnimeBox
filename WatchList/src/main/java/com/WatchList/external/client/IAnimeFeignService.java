package com.WatchList.external.client;

import com.WatchList.external.AnimeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ANIME-CATALOG/anime")
public interface IAnimeFeignService {
    
    @GetMapping("/{id}")
    public AnimeResponseDto getAnimeById(@PathVariable("id") Long animeId);
    
}
