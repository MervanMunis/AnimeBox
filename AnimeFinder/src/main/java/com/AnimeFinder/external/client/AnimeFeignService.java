package com.AnimeFinder.external.client;

import com.AnimeFinder.external.dto.AnimeDto;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ANIME-CATALOG/anime")
public interface AnimeFeignService {

    @GetMapping("/allAnimeList")
    List<AnimeDto> getAllAnime();
}
