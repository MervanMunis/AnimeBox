package com.WatchList.service;

import com.WatchList.mapper.AnimeMapper;
import com.WatchList.entity.AnimeItem;
import com.WatchList.exception.ResourceNotFoundException;
import com.WatchList.external.AnimeResponseDto;
import com.WatchList.external.client.IAnimeFeignService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.WatchList.repository.IAnimeRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AnimeListServiceImpl implements IAnimeListService{
    
    private final IAnimeRepository animeRepository;
    
    private final IAnimeFeignService animeFeign;

    @Override
    public String create(Long animeId) {
        log.info("Getting The Anime From AnimeCatalog (Feign Client)...");
        AnimeResponseDto animeResponseDto = animeFeign.getAnimeById(animeId);
        
        log.info("The Anime Map to Dto");
        AnimeItem animeItem = AnimeMapper.mapToAnimeItem(animeResponseDto);
        
        animeRepository.save(animeItem);
        
        log.info("Added the anime to the watchlist.");
        return "Added the anime to the watchlist!";
    }

    @Override
    public List<AnimeResponseDto> getWatchList() {
        log.info("Finding All Animes...");
        
        List<AnimeItem> animeItems = animeRepository.findAll();
        
        log.info("Animes map to Dtos");
        
        List<AnimeResponseDto> animeItemDtos = animeItems.stream()
                .map(animeItem -> AnimeMapper.mapToAnimeResponseDto(animeItem))
                .toList();
        
        log.info("Wathc List Is Returned");
        return animeItemDtos;
    }

    @Override
    public String delete(Long id) {
        
        log.info("Finding The Anime to Remove...");
        AnimeItem animeItem = animeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The Anime is Not Found with: " + id,
                        "Anime ANIME_NOT_FOUND",
                        404));
        
        log.info("Deleteing The Anime...");
        animeRepository.delete(animeItem);
        
        log.info("The Anime Is Deleted.");
        return "The Anime Is Removed";
    }
    
}
