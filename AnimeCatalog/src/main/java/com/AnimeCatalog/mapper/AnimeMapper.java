package com.AnimeCatalog.mapper;

import com.AnimeCatalog.dto.AnimeRequestDto;
import com.AnimeCatalog.dto.AnimeResponseDto;
import com.AnimeCatalog.entity.Anime;

public class AnimeMapper {
    public static Anime mapToAnime(AnimeRequestDto animeRequestDto) {
        return Anime.builder()
                .id(animeRequestDto.getId())
                .animeName(animeRequestDto.getAnimeName())
                .subject(animeRequestDto.getSubject())
                .category(animeRequestDto.getCategory())
                .history(animeRequestDto.getHistory())
                .imdb(animeRequestDto.getImdb())
                .numberOfEpisodes(animeRequestDto.getNumberOfEpisodes())
//                .image(animeRequestDto.getImage())
                .build();
    }

    public static AnimeResponseDto mapToAnimeResponseDto(Anime anime) {

        return AnimeResponseDto.builder()
                .id(anime.getId())
                .animeName(anime.getAnimeName())
                .subject(anime.getSubject())
                .category(anime.getCategory())
                .history(anime.getHistory())
                .imdb(anime.getImdb())
                .numberOfEpisodes(anime.getNumberOfEpisodes())
//                .image(anime.getImage())
                .build();
    }
}
