package com.AnimeFinder.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInput {
    private String user_input;

    @ElementCollection
    @CollectionTable(name = "CATEGORY")
    private Set<String> category;

    private Integer releaseYear;

    private Float imdb;
}
