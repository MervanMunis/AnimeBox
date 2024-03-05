package com.WatchList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ANIME_LIST")
public class AnimeItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ANIME_NAME")
    private String animeName;

    @Column(name = "HISTORY")
    private LocalDate history;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "NUMBER_OF_EPİSODES")
    private int numberOfEpisodes;

    @Column(name = "IMDB")
    private float imdb;
 
}
