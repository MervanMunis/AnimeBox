package com.AnimeFinder.service;

import com.AnimeFinder.external.client.IAnimeFeignService;
import com.AnimeFinder.external.client.IGeminiApiFeignService;
import com.AnimeFinder.external.dto.AnimeResponseDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AnimeFinderServiceImpl implements IAnimeFinderService {
    
    private final IGeminiApiFeignService gemini;
    private final IAnimeFeignService animeFeignService;
//    private final IOllamaAilFeignService ollamaService;
//    private final RestTemplate restTemplate;
    
    
    @Override
    public List<AnimeResponseDto> animeFinderBot(String message) {
        log.info("Getting all animes from AnimeCatalog by using feign service...");
        List<AnimeResponseDto> animes = animeFeignService.getAllAnime();
        log.info("The response from animeCatalog is SUCCESSFUL.");
        
        String animesString = animes.stream()
                .map(anime -> "[" + anime.getAnimeName() + ", "  + 
                        anime.getCategory() + ", " +
                        anime.getSubject() + ", " +
                        anime.getHistory() + ", " +
                        anime.getImdb() + ", " + "]")
                .collect(Collectors.joining(", "));        
        
        String spesificationMessage = 
                "Anime Categories, Essential Keywords: " +
                "Kodomomuke, Shonen, Shojo, Seinen, Josei, Action, Adventure, Comedy," +
                "Drama, Fantasy, Historical, Horror, Mecha, Mystery, Romance, Science Fiction," +
                "Slice of Life, Sports, Supernatural, Thriller, Martial Arts, " +
                "Psychological, School Life, Music, Harem. " +
                "Now I will provide you my anime list with all informations from my database: " +  animesString +
                "I will share user messages, and I need you to analyze the content, considering similarities in " +
                "topics, categories, historical context, and IMDb ratings. Subsequently, generate anime " +
                "recommendations based on these patterns. Please include full anime names and seperate them with commas. " +
                "Provide a maximum of 3 recommendations In this pattern only: [anime_full_name 1, anime_full_name 2, anime_full_name 3]. " +
                "DO NOT WRİTE ANYTHİNG ELSE. The user message: " + message;
        
//        log.info("Getting response from OllamaService by using feign service...");
//        String response = ollamaService.createChat(spesificationMessage + message);

        log.info("creating request body for gemini_api");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_input", spesificationMessage);
        
        log.info("Posting a request to gemini_api by using gemini feign service...");
        String response = gemini.getResponse(requestBody);
        log.info("The response from gemini_api is SUCCESSFUL.", response);
        
        log.info("Parsing the response from gemini_api...");
        List<String> recommendedAnimeList = parseResponse(response);   
        
        log.info("Getting the recomeneded anime list...");
        List<AnimeResponseDto> matchingAnimeList = getMatchingAnimeIds(recommendedAnimeList, animes);
               
        log.info("Recomended animes are listed.");
        return matchingAnimeList;
    }
    
    private List<String> parseResponse(String response) {
        // Assuming the response is in the format: "[anime_name_1, anime_name_2, anime_name_3]"
        response = response.replaceAll("[\\[\\]]", ""); // Remove brackets and spaces
        return Arrays.asList(response.split(", "));
    }
    
    private List<AnimeResponseDto> getMatchingAnimeIds(List<String> recommendedAnimeList, List<AnimeResponseDto> animes) {
        List<AnimeResponseDto> matchingIds = new ArrayList<>();
        
        for (String recommendedAnimeName : recommendedAnimeList) {
            Optional<AnimeResponseDto> matchingAnime = animes.stream()
                    .filter(anime -> recommendedAnimeName.equalsIgnoreCase(anime.getAnimeName()))
                    .findFirst();
            
            matchingAnime.ifPresent(animeResponseDto -> matchingIds.add(animeResponseDto));
        }
        
        return matchingIds;
    }
}
