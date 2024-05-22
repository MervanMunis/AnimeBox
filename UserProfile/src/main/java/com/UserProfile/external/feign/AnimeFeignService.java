package com.UserProfile.external.feign;

import com.UserProfile.dto.AnimeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ANIME-CATALOG/anime")
public interface AnimeFeignService {

    @GetMapping("/{animeId}")
    AnimeDto getAnimeById(@PathVariable("animeId") Integer animeId);
}
