package com.AnimeFinder.external.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class AnimeResponseDto {

    private long id;
    private String animeName;
    private LocalDate history;
    private String category;
    private String subject;
    private int numberOfEpisodes;
    private float imdb;
//    private byte[] image;
}
