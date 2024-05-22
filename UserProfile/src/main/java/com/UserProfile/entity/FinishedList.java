package com.UserProfile.entity;

import com.UserProfile.dto.AnimeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinishedList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "finished_list_animes", joinColumns = @JoinColumn(name = "finished_list_id"))
    private Set<AnimeDto> animes = new HashSet<>();
}
