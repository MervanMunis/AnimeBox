package com.AnimeFinder.external.client;

import com.AnimeFinder.external.dto.AnimeResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ANIME-CATALOG/anime")
public interface IAnimeFeignService {
    
    @GetMapping
    List<AnimeResponseDto> getAllAnime();
    
}
