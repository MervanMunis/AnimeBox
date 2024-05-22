package com.AnimeCatalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animeId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Please provide anime's name!")
    private String animeName;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Please provide anime's director!")
    private String director;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Please provide anime's studio!")
    private String studio;

    @ElementCollection
    @CollectionTable(name = "ANIME_CAST")
    private Set<String> animeCast;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Please provide anime's release year!")
    private Integer releaseYear;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Please provide the country name that the anime is produced!")
    private String country;

    @ElementCollection
    @CollectionTable(name = "CATEGORY")
    private Set<String> category;

    @Column(nullable = false)
    @NotBlank(message = "Please provide the number of the season!")
    private Integer seasonNo;

    @Column(nullable = false)
    @NotBlank(message = "Please provide the number of episodes!")
    private Integer numberOfEpisodes;

    private Float imdb;

    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's poster!")
    private String poster;
}
