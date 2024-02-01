package com.AnimeCatalog.service;

import com.AnimeCatalog.dto.AnimeRequestDto;
import com.AnimeCatalog.dto.AnimeResponseDto;

import java.util.List;

public interface IAnimeService {

    AnimeResponseDto createAnime(AnimeRequestDto animeRequestDto);

    AnimeResponseDto getAnimeById(Long animeId);

    List<AnimeResponseDto> getAllAnime();

    List<AnimeResponseDto> getAllAnimeByCategory(String category);

    AnimeResponseDto updateAnime(Long animeId, AnimeRequestDto animeRequestDto);

    String deleteAnime(Long animeId);

}
