package com.AnimeCatalog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "animes")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ANIME_NAME")
    private String animeName;

    @Column(name = "HISTORY")
    private LocalDate history;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "SUBJECT")
    private TextArea subject;

    @Column(name = "NUMBER_OF_EPİSODES")
    private int numberOfEpisodes;

    @Column(name = "IMDB")
    private float imdb;

    @Lob
    @Column(name = "IMAGE", columnDefinition = "longblob")
    private byte[] image;
}
