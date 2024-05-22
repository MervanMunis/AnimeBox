package com.UserProfile.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDto implements Serializable {

    private Integer animeId;
    private String animeName;
    private String director;
    private String studio;
    private Set<String> animeCast;
    private Integer releaseYear;
    private String country;
    private Set<String> category;
    private Integer seasonNo;
    private Integer numberOfEpisodes;
    private Float imdb;
    private String poster;
}

