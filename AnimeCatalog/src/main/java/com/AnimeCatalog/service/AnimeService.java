package com.AnimeCatalog.service;

import com.AnimeCatalog.dto.AnimeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AnimeService {

    AnimeDto createAnime(AnimeDto animeDto, MultipartFile file) throws IOException;

    AnimeDto getAnimeById(Integer animeId);

    List<AnimeDto> getAllAnime();

    List<AnimeDto> getAllAnimeByCategory(Set<String> category);

    AnimeDto updateAnime(Integer animeId, AnimeDto animeDto, MultipartFile file) throws IOException;

    String deleteAnime(Integer animeId) throws IOException;

}
