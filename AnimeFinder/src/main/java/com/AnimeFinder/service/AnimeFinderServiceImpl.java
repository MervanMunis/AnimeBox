package com.AnimeFinder.service;

import com.AnimeFinder.external.client.AnimeFeignService;
import com.AnimeFinder.external.client.GeminiApiFeignService;
import com.AnimeFinder.external.dto.AnimeDto;

import java.util.*;
import java.util.stream.Collectors;

import com.AnimeFinder.dto.UserInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnimeFinderServiceImpl implements AnimeFinderService {

    private final GeminiApiFeignService geminiFeignService;
    private final AnimeFeignService animeFeignService;
    private final ObjectMapper objectMapper;

    public AnimeFinderServiceImpl(GeminiApiFeignService geminiFeignService,
                                  AnimeFeignService animeFeignService,
                                  ObjectMapper objectMapper){
        this.geminiFeignService = geminiFeignService;
        this.animeFeignService = animeFeignService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<AnimeDto> animeFinderBot(UserInput userInput) {
        log.info("Getting all anime from AnimeCatalog by using feign service...");

        List<AnimeDto> animeList = animeFeignService.getAllAnime();

        log.info("The response from animeCatalog is SUCCESSFUL.");

        // Apply filters
        List<AnimeDto> filteredAnimeList = animeList.stream()
                .filter(anime -> userInput.getCategory() == null || userInput.getCategory().isEmpty() || !Collections.disjoint(anime.getCategory(), userInput.getCategory()))
                .filter(anime -> userInput.getImdb() == null || anime.getImdb() >= userInput.getImdb())
                .filter(anime -> userInput.getReleaseYear() == null || anime.getReleaseYear() >= userInput.getReleaseYear())
                .toList();

        String animesString = filteredAnimeList.stream()
                .map(AnimeDto::getAnimeName)
                .collect(Collectors.joining(", ", "[", "]"));

        String specificationMessage =
                "You are an anime recommendation system. Analyze the provided anime list and user message " +
                        "to generate personalized anime recommendations. " +
                        "Consider similarities in topics, categories, historical context, and IMDb ratings. " +
                        "Use the following criteria and data for your analysis: " +
                        "\n\n" +
                        "Anime Categories: Kodomomuke, Shonen, Shojo, Seinen, Josei, Action, Adventure, " +
                        "Comedy, Drama, Fantasy, Historical, Horror, Mecha, Mystery, Romance, Science Fiction, " +
                        "Slice of Life, Sports, Supernatural, Thriller, Martial Arts, Psychological, School Life, " +
                        "Music, Harem. " +
                        "\n\n" +
                        "Anime List: " + animesString +
                        "\n\n" +
                        "User Message: \"" + userInput.getUser_input() + "\"\n" +
                        "Minimum IMDb Rating: " + (userInput.getImdb() != null ? userInput.getImdb() : "None") + "\n" +
                        "Minimum Release Year: " + (userInput.getReleaseYear() != null ? userInput.getReleaseYear() : "None") +
                        "\n\n" +
                        "Instructions:\n" +
                        "1. Analyze the user message and the provided anime list.\n" +
                        "2. Identify anime that match the user's preferences based on categories, IMDb rating, and release year.\n" +
                        "3. Provide a list of at least 2 recommended animes in the following JSON format:\n" +
                        "[{'animeName': 'animeName1'}, {'animeName': animeName2}, {'animeName': animeName3}].\n" +
                        "4. Do not include any other text in your response.";

        log.info("Creating request body for gemini_api");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_input", specificationMessage);

        log.info("Posting a request to gemini_api by using gemini feign service...");

        String response = geminiFeignService.getResponse(requestBody);

        log.info("The response from gemini_api is SUCCESSFUL.");

        log.info("Parsing the response from gemini_api...");

        List<AnimeDto> matchingAnimeList = parseResponse(response, filteredAnimeList);

        log.info("Recommended anime are listed.");

        return matchingAnimeList;
    }

    private List<AnimeDto> parseResponse(String response, List<AnimeDto> animeDtoList) {
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            String recommendedAnimesString = rootNode.path("recommended_animes").asText();

            recommendedAnimesString = recommendedAnimesString.replace("'", "\"");

            // Parse the nested JSON string
            List<Map<String, String>> recommendedAnimes = objectMapper
                    .readValue(recommendedAnimesString, new TypeReference<>(){});

            List<String> recommendedAnimeList = recommendedAnimes.stream()
                    .map(animeDto -> animeDto.get("animeName"))
                    .toList();

            return getMatchingAnimeIds(recommendedAnimeList, animeDtoList);

        } catch (JsonProcessingException ex) {
            log.info("Error parsing JSON response: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    private List<AnimeDto> getMatchingAnimeIds(List<String> recommendedAnimeList, List<AnimeDto> animeList) {
        return animeList.stream()
                .filter(anime -> recommendedAnimeList.contains(anime.getAnimeName()))
                .collect(Collectors.toList());
    }
}
