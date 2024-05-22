package com.AnimeCatalog.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AnimeDto {

    private Integer animeId;

    @NotBlank(message = "Please provide anime's name!")
    private String animeName;

    @NotBlank(message = "Please provide anime's director!")
    private String director;

    @NotBlank(message = "Please provide anime's studio!")
    private String studio;

    private Set<String> animeCast;

    private Integer releaseYear;

    @NotBlank(message = "Please provide the country name that the anime is produced!")
    private String country;

    @ElementCollection
    @CollectionTable(name = "CATEGORY")
    private Set<String> category;

    @NotBlank(message = "Please provide the number of the season!")
    private Integer seasonNo;

    private Integer numberOfEpisodes;

    private Float imdb;

    @NotBlank(message = "Please provide movie's poster!")
    private String poster;
}
