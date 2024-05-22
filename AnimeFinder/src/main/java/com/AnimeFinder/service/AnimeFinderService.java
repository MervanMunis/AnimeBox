package com.AnimeFinder.service;

import com.AnimeFinder.external.dto.AnimeDto;
import com.AnimeFinder.dto.UserInput;

import java.util.List;

public interface AnimeFinderService {

    List<AnimeDto> animeFinderBot(UserInput userInput);
}
