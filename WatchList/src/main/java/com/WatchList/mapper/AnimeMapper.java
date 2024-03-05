package com.WatchList.mapper;

import com.WatchList.cdto.AnimeRequestDto;
import com.WatchList.entity.AnimeItem;
import com.WatchList.external.AnimeResponseDto;

public class AnimeMapper {
    
    public static AnimeItem mapToAnimeItem(AnimeResponseDto animeResponseDto) {
        return AnimeItem.builder()
                .id(animeResponseDto.getId())
                .animeName(animeResponseDto.getAnimeName())
                .history(animeResponseDto.getHistory())
                .category(animeResponseDto.getCategory())
                .subject(animeResponseDto.getSubject())
                .numberOfEpisodes(animeResponseDto.getNumberOfEpisodes())
                .imdb(animeResponseDto.getImdb())
                .build();
    }

    public static AnimeResponseDto mapToAnimeResponseDto(AnimeItem animeItem) {
        return AnimeResponseDto.builder()
                .id(animeItem.getId())
                .animeName(animeItem.getAnimeName())
                .history(animeItem.getHistory())
                .category(animeItem.getCategory())
                .subject(animeItem.getSubject())
                .numberOfEpisodes(animeItem.getNumberOfEpisodes())
                .imdb(animeItem.getImdb())
                .build();
    }

   
}
