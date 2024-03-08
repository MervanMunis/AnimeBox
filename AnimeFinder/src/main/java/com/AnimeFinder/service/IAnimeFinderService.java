package com.AnimeFinder.service;

import com.AnimeFinder.external.dto.AnimeResponseDto;
import java.util.List;

public interface IAnimeFinderService {
    
    List<AnimeResponseDto> animeFinderBot(String message);
}
