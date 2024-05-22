package com.AnimeCatalog.mapper;

import com.AnimeCatalog.dto.AnimeDto;
import com.AnimeCatalog.entity.Anime;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public class AnimeMapper {
    public static Anime mapToAnime(AnimeDto animeDto, String file) {
        return Anime.builder()
                .animeName(animeDto.getAnimeName())
                .director(animeDto.getDirector())
                .studio(animeDto.getStudio())
                .animeCast(animeDto.getAnimeCast())
                .releaseYear(animeDto.getReleaseYear())
                .country(animeDto.getCountry())
                .category(animeDto.getCategory())
                .seasonNo(animeDto.getSeasonNo())
                .numberOfEpisodes(animeDto.getNumberOfEpisodes())
                .imdb(animeDto.getImdb())
                .poster(file)
                .build();
    }

    public static AnimeDto mapToAnimeDto(Anime anime) {
        return AnimeDto.builder()
                .animeId(anime.getAnimeId())
                .animeName(anime.getAnimeName())
                .director(anime.getDirector())
                .studio(anime.getStudio())
                .animeCast(anime.getAnimeCast())
                .releaseYear(anime.getReleaseYear())
                .country(anime.getCountry())
                .category(anime.getCategory())
                .seasonNo(anime.getSeasonNo())
                .numberOfEpisodes(anime.getNumberOfEpisodes())
                .imdb(anime.getImdb())
                .poster(anime.getPoster())
                .build();
    }

    public static Anime mapToUpdateAnime(AnimeDto animeDto, String file){
        return new Anime(
                animeDto.getAnimeId(),
                animeDto.getAnimeName(),
                animeDto.getDirector(),
                animeDto.getStudio(),
                animeDto.getAnimeCast(),
                animeDto.getReleaseYear(),
                animeDto.getCountry(),
                animeDto.getCategory(),
                animeDto.getSeasonNo(),
                animeDto.getNumberOfEpisodes(),
                animeDto.getImdb(),
                file
        );
    }
}
