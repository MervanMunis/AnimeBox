package com.WatchList.service;

import com.WatchList.external.AnimeResponseDto;
import java.util.List;

public interface IAnimeListService {
    
    String create(Long animeId);
    
    List<AnimeResponseDto> getWatchList();
    
    String delete(Long id);
}
