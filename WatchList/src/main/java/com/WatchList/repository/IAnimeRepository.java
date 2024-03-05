package com.WatchList.repository;

import com.WatchList.entity.AnimeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnimeRepository extends JpaRepository<AnimeItem, Long>{
    
}
