package com.AnimeCatalog.service.impl;

import com.AnimeCatalog.dto.AnimeRequestDto;
import com.AnimeCatalog.dto.AnimeResponseDto;
import com.AnimeCatalog.entity.Anime;
import com.AnimeCatalog.exception.ResourceNotFoundException;
import com.AnimeCatalog.mapper.AnimeMapper;
import com.AnimeCatalog.repository.IAnimeRepository;
import com.AnimeCatalog.service.IAnimeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AnimeServiceImpl implements IAnimeService {

    private final IAnimeRepository animeRepository;

    @Override
    public AnimeResponseDto createAnime(AnimeRequestDto animeRequestDto) {
        log.info("Creating Anime...");

        Anime anime = AnimeMapper.mapToAnime(animeRequestDto);

        log.info("Checking If The Anime Exists Or Not...");
        boolean isExists = animeRepository.existsByAnimeName(anime.getAnimeName());

        if (!isExists) {
            log.info("Saving Anime...");

            Anime animeSaved = animeRepository.save(anime);
            log.info("Anime Is Created.");

            return AnimeMapper.mapToAnimeResponseDto(animeSaved);
        } else {
            log.info("Anime Already Exists.");
            return null;
        }
    }

    @Override
    public AnimeResponseDto getAnimeById(Long animeId) {
        log.info("Get Anime With The animeId: {} ", animeId);

        Anime anime = animeRepository.findById(animeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Anime Does Not Exist With The Given Id: " + animeId,
                        "ANIME_NOT_FOUND",
                        404
                )
        );

        log.info("Anime Is Returned.");
        return AnimeMapper.mapToAnimeResponseDto(anime);
    }

    @Override
    public List<AnimeResponseDto> getAllAnime() {
        log.info("Getting All Anime...");

        List<Anime> anime = animeRepository.findAll().stream()
                .sorted(Comparator.comparing(Anime::getAnimeName))
                .toList();

        log.info("All Anime Listed Alphabetically.");
        return anime.stream()
                .map(AnimeMapper::mapToAnimeResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimeResponseDto> getAllAnimeByCategory(String category) {
        log.info("Getting All Anime By Single Category...");

        List<Anime> animeCategory = animeRepository.findAnimeByCategory(category);

        log.info("All Anime Listed By Single Category.");
        return animeCategory.stream()
                .map(AnimeMapper::mapToAnimeResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnimeResponseDto updateAnime(Long animeId, AnimeRequestDto animeRequestDto) {
        log.info("Get Anime With The animeId: {} To Update...", animeId);

        Anime anime = animeRepository.findById(animeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Anime Does Not Exist With The Given Id: " + animeId,
                        "ANIME_NOT_FOUND",
                        404
                )
        );

        AnimeResponseDto animeResponseDto = AnimeMapper.mapToAnimeResponseDto(anime);

        log.info("Updating The Anime...");
        animeResponseDto.setId(animeRequestDto.getId());
        animeResponseDto.setAnimeName(animeRequestDto.getAnimeName());
        animeResponseDto.setSubject(animeRequestDto.getSubject());
        animeResponseDto.setCategory(animeRequestDto.getCategory());
        animeResponseDto.setHistory(animeRequestDto.getHistory());
        animeResponseDto.setImdb(animeRequestDto.getImdb());
        animeResponseDto.setNumberOfEpisodes(animeRequestDto.getNumberOfEpisodes());
//        animeResponseDto.setImage(animeRequestDto.getImage());

        log.info("Anime Is Updated");
        return animeResponseDto;
    }

    @Override
    public String deleteAnime(Long animeId) {
        log.info("Deleting The Anime With animeId: {}...", animeId);

        Anime anime = animeRepository.findById(animeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Anime Does Not Exist With The Given Id: " + animeId,
                        "ANIME_NOT_FOUND",
                        404
                )
        );

        animeRepository.delete(anime);
        log.info("Anime Is Deleted.");
        return "Anime Is Deleted.";
    }
}
