package com.AnimeCatalog.repository;

import com.AnimeCatalog.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    List<Anime> findAnimeByCategoryIn(Set<String> category);
    List<Anime> findAnimeByImdb(Float imdb);
    boolean existsByAnimeName(String animeName);
}
