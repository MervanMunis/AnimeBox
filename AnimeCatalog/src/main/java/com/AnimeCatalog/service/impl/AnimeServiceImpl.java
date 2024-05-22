package com.AnimeCatalog.service.impl;

import com.AnimeCatalog.dto.AnimeDto;
import com.AnimeCatalog.entity.Anime;
import com.AnimeCatalog.exception.FileExistsException;
import com.AnimeCatalog.exception.ResourceNotFoundException;
import com.AnimeCatalog.mapper.AnimeMapper;
import com.AnimeCatalog.repository.AnimeRepository;
import com.AnimeCatalog.service.AnimeService;
import com.AnimeCatalog.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnimeServiceImpl implements AnimeService {


    private final String path;
    private final AnimeRepository animeRepository;
    private final FileService fileService;

    public AnimeServiceImpl(AnimeRepository animeRepository, FileService fileService, @Value("${project.poster}") String path){
        this.animeRepository = animeRepository;
        this.fileService = fileService;
        this.path = path;
    }

    @Override
    public AnimeDto createAnime(AnimeDto animeDto, MultipartFile file) throws IOException {
        log.info("Creating Anime...");
        String filePathName = path + File.separator + file.getOriginalFilename();

        if(Files.exists(Paths.get(filePathName))) {
            throw new FileExistsException("File already exists! Please enter an other file name!");
        }

        log.info("Uploading the file...");
        String uploadedFileName = fileService.uploadFile(path, file);
        Anime anime = AnimeMapper.mapToAnime(animeDto, uploadedFileName);

        log.info("Checking If The Anime Exists Or Not...");

        boolean isExists = animeRepository.existsByAnimeName(anime.getAnimeName());

        if (Boolean.FALSE.equals(isExists)) {
            log.info("Saving Anime...");
            Anime animeSaved = animeRepository.save(anime);

            log.info("Anime Is Created.");
            return AnimeMapper.mapToAnimeDto(animeSaved);
        } else {
            log.info("Anime Already Exists.");

            return null;
        }
    }

    @Override
    public AnimeDto getAnimeById(Integer animeId) {
        log.info("Get Anime With The animeId: {} ", animeId);

        Anime anime = animeRepository.findById(animeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Anime Does Not Exist With The Given Id: " + animeId,
                        "ANIME_NOT_FOUND",
                        404
                )
        );

        log.info("Anime Is Returned.");
        return AnimeMapper.mapToAnimeDto(anime);
    }

    @Override
    public List<AnimeDto> getAllAnime() {
        log.info("Getting All Anime...");
        List<Anime> animeList = animeRepository.findAll().stream()
                .sorted(Comparator.comparing(Anime::getAnimeName))
                .toList();

        log.info("All Anime Listed Alphabetically.");
        return animeList.stream()
                .map(AnimeMapper::mapToAnimeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimeDto> getAllAnimeByCategory(Set<String> categories) {
        log.info("Getting All Anime By Single Category...");
        List<Anime> animeCategory = animeRepository.findAnimeByCategoryIn(categories);

        log.info("All Anime Listed By Single Category.");
        return animeCategory.stream()
                .map(AnimeMapper::mapToAnimeDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnimeDto updateAnime(Integer animeId, AnimeDto animeDto, MultipartFile file) throws IOException {
        log.info("Get Anime With The animeId: {} To Update...", animeId);
        Anime currentAnime = animeRepository.findById(animeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Anime Does Not Exist With The Given Id: " + animeId,
                        "ANIME_NOT_FOUND",
                        404
                )
        );

        String fileName = currentAnime.getPoster();
        log.info("Checking if the file is not null to update the file: {}", fileName);
        if(file!=null){
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
            log.info("The file has been updated.");
        }

        log.info("Saving the updates in database...");
        Anime updatedAnime = AnimeMapper.mapToUpdateAnime(animeDto, fileName);
        Anime savedAnime = animeRepository.save(updatedAnime);

        log.info("Anime Is Updated");
        return AnimeMapper.mapToAnimeDto(savedAnime);
    }

    @Override
    public String deleteAnime(Integer animeId) throws IOException {
        log.info("Deleting The Anime With animeId: {}...", animeId);

        Anime anime = animeRepository.findById(animeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Anime Does Not Exist With The Given Id: " + animeId,
                        "ANIME_NOT_FOUND",
                        404
                )
        );

        Files.deleteIfExists(Paths.get(path + File.separator + anime.getPoster()));

        log.info("Deleting the anime...");
        animeRepository.delete(anime);

        log.info("Anime Is Deleted.");
        return "Anime Is Deleted.";
    }
}
