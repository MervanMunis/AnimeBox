package com.UserProfile.service.impl;

import com.UserProfile.dto.AuthUserDto;
import com.UserProfile.dto.UserInfoDto;
import com.UserProfile.dto.UserProfileDto;
import com.UserProfile.entity.*;
import com.UserProfile.exception.ResourceNotFoundException;
import com.UserProfile.dto.AnimeDto;
import com.UserProfile.external.feign.AnimeFeignService;
import com.UserProfile.mapper.UserProfileMapper;
import com.UserProfile.repotsitory.UserProfileRepository;
import com.UserProfile.service.FileService;
import com.UserProfile.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final AnimeFeignService feignService;
    private final FileService fileService;
    private final String path;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository,
                                  AnimeFeignService feignService,
                                  FileService fileService,
                                  @Value("${project.image}") String path){
        this.userProfileRepository = userProfileRepository;
        this.feignService = feignService;
        this.fileService = fileService;
        this.path = path;
    }

    @Override
    public String createUserProfile(AuthUserDto authUserDto) {
        // A default jpeg is provided in image folder.
        UserProfile userProfile = UserProfileMapper.mapToUserProfile(authUserDto, "default.jpeg");

        log.info("Checking If The User Exists Or Not");

        boolean isExists = userProfileRepository.existsByUsername(authUserDto.getUsername());

        if(Boolean.FALSE.equals(isExists)){
            log.info("Saving User Profile...");

            userProfileRepository.save(userProfile);

            log.info("User Is Saved.");
            return "User Is Saved In Database.";
        }else {
            return "The User Is Already Exists With This Username or Id: " + authUserDto.getUsername() + "!";
        }
    }

    @Override
    public UserProfileDto getUserProfileByUserId(UUID userId) {
        UserProfile userProfile = findingUserById(userId);
        return UserProfileMapper.mapToUserProfileDto(userProfile);
    }

    @Override
    public List<UserProfileDto> getAllUserProfiles() {
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        return userProfileList.stream()
                .map(UserProfileMapper::mapToUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAnimeInWatchList(UUID userId, Integer animeId){
        AnimeDto animeDto = feignService.getAnimeById(animeId);

        UserProfile userProfile = findingUserById(userId);

        WatchList watchList = userProfile.getWatchList();

        if (watchList == null) {
            watchList = new WatchList();
            userProfile.setWatchList(watchList);
        }

        watchList.getAnimes().add(animeDto);
        userProfileRepository.save(userProfile);
    }

    @Override
    public void updateAnimeInWatchingList(UUID userId, Integer animeId) {
        AnimeDto animeDto = feignService.getAnimeById(animeId);

        UserProfile userProfile = findingUserById(userId);

        WatchingList watchingList = userProfile.getWatchingList();
        if (watchingList == null) {
            watchingList = new WatchingList();
            userProfile.setWatchingList(watchingList);
        }

        watchingList.getAnimes().add(animeDto);
        userProfileRepository.save(userProfile);
    }

    @Override
    public void updateAnimeInFinishedList(UUID userId, Integer animeId) {
        AnimeDto animeDto = feignService.getAnimeById(animeId);

        UserProfile userProfile = findingUserById(userId);

        FinishedList finishedList = userProfile.getFinishedList();
        if (finishedList == null) {
            finishedList = new FinishedList();
            userProfile.setFinishedList(finishedList);
        }

        finishedList.getAnimes().add(animeDto);
        userProfileRepository.save(userProfile);
    }

    @Override
    public void updateAnimeInDroppedList(UUID userId, Integer animeId) {
        AnimeDto animeDto = feignService.getAnimeById(animeId);

        UserProfile userProfile = findingUserById(userId);

        DroppedList droppedList = userProfile.getDroppedList();
        if (droppedList == null) {
            droppedList = new DroppedList();
            userProfile.setDroppedList(droppedList);
        }

        droppedList.getAnimes().add(animeDto);
        userProfileRepository.save(userProfile);
    }

    @Override
    public void updateUserInfoById(UUID userId, UserInfoDto userInfoDto, MultipartFile file) throws IOException {
        UserProfile currentUserProfile = findingUserById(userId);

        String fileName = currentUserProfile.getImage();

        log.info("Checking if the file is not null to update the file: {}", fileName);

        if (file != null) {
            if (!Objects.equals(fileName, "default.jpeg")) {
                Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            }
            fileName = fileService.uploadFile(path, file);

            log.info("The file has been updated.");
        }

        UserProfile updatedUserInfo = UserProfileMapper.mapToUpdateUserInfo(currentUserProfile, userInfoDto, fileName);
        userProfileRepository.save(updatedUserInfo);

        log.info("User info is updated.");
    }

    @Override
    public void deleteAnimeInWatchList(UUID userId, Integer animeId) {
        // Find the UserProfile by userId
        UserProfile userProfile = findingUserById(userId);

        // Retrieve the WatchList entity and ensure it is not null
        WatchList watchList = userProfile.getWatchList();

        boolean removed = watchList
                .getAnimes()
                .removeIf(animeDto -> Objects.equals(animeDto.getAnimeId(), animeId));

        if (Boolean.FALSE.equals(removed)) {
            // If the anime was not found in the watchlist
            throw new ResourceNotFoundException(
                    "Anime with ID " + animeId + " not found in the watch list of user " + userId,
                    "ANIME_NOT_FOUND",
                    404
            );
        }
        userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteAnimeInWatchingList(UUID userId, Integer animeId) {
        // Find the UserProfile by userId
        UserProfile userProfile = findingUserById(userId);

        // Retrieve the WatchingList entity and ensure it is not null
        WatchingList watchingList = userProfile.getWatchingList();

        boolean removed = watchingList
                .getAnimes()
                .removeIf(animeDto -> Objects.equals(animeDto.getAnimeId(), animeId));

        if (Boolean.FALSE.equals(removed)) {
            // If the anime was not found in the watching list
            throw new ResourceNotFoundException(
                    "Anime with ID " + animeId + " not found in the watching list of user " + userId,
                    "ANIME_NOT_FOUND",
                    404
            );
        }
        userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteAnimeInFinishedList(UUID userId, Integer animeId) {
        // Find the UserProfile by userId
        UserProfile userProfile = findingUserById(userId);

        // Retrieve the FinishedList entity and ensure it is not null
        FinishedList finishedList = userProfile.getFinishedList();

        boolean removed = finishedList
                .getAnimes()
                .removeIf(animeDto -> Objects.equals(animeDto.getAnimeId(), animeId));

        if (Boolean.FALSE.equals(removed)) {
            // If the anime was not found in the watching list
            throw new ResourceNotFoundException(
                    "Anime with ID " + animeId + " not found in the finished list of user " + userId,
                    "ANIME_NOT_FOUND",
                    404
            );
        }
        userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteAnimeInDroppedList(UUID userId, Integer animeId) {
        // Find the UserProfile by userId
        UserProfile userProfile = findingUserById(userId);

        // Retrieve the DroppedList entity and ensure it is not null
        DroppedList droppedList = userProfile.getDroppedList();

        boolean removed = droppedList
                .getAnimes()
                .removeIf(animeDto -> Objects.equals(animeDto.getAnimeId(), animeId));

        if (Boolean.FALSE.equals(removed)) {
            // If the anime was not found in the dropped list
            throw new ResourceNotFoundException(
                    "Anime with ID " + animeId + " not found in the dropped list of user " + userId,
                    "ANIME_NOT_FOUND",
                    404
            );
        }
        userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteUserProfile(UUID userId) throws IOException {
        log.info("Deleting The User With userId: {}...", userId);

        UserProfile userProfile = findingUserById(userId);

        Files.deleteIfExists(Paths.get(path + File.separator + userProfile.getImage()));

        log.info("Deleting the user...");
        userProfileRepository.delete(userProfile);

        log.info("User Is Deleted.");
    }

    private UserProfile findingUserById(UUID userId){
        log.info("Finding The User With userId: {}... For Watching List", userId);

        return userProfileRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User Does Not Exist With The Given Id: " + userId,
                        "USER_NOT_FOUND",
                        404
                )
        );
    }
}
