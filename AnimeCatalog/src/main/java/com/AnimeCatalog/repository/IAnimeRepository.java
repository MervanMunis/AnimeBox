package com.AnimeCatalog.repository;

import com.AnimeCatalog.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimeRepository extends JpaRepository<Anime, Long> {

    List<Anime> findAnimeByCategory(String category);
    List<Anime> findAnimeByImdb(Float imdb);
    boolean existsByAnimeName(String animeName);

}
