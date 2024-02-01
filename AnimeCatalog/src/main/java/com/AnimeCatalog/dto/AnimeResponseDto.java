package com.AnimeCatalog.dto;

import lombok.*;

import java.awt.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AnimeResponseDto {

    private long id;
    private String animeName;
    private LocalDate history;
    private String category;
    private TextArea subject;
    private int numberOfEpisodes;
    private float imdb;
    private byte[] image;
}
