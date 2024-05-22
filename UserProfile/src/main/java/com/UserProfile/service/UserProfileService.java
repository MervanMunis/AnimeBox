package com.UserProfile.service;

import com.UserProfile.dto.AuthUserDto;
import com.UserProfile.dto.UserInfoDto;
import com.UserProfile.dto.UserProfileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserProfileService {

    String createUserProfile(AuthUserDto authUserDto);

    void updateAnimeInWatchList(UUID userId, Integer animeId);

    void updateAnimeInWatchingList(UUID userId, Integer animeId);

    void updateAnimeInFinishedList(UUID userId, Integer animeId);

    void updateAnimeInDroppedList(UUID userId, Integer animeId);

    UserProfileDto getUserProfileByUserId(UUID userId);

    List<UserProfileDto> getAllUserProfiles();

    void updateUserInfoById(UUID userId, UserInfoDto userInfoDto, MultipartFile file) throws IOException;

    void deleteUserProfile(UUID userId) throws IOException;

    void deleteAnimeInWatchList(UUID userId, Integer animeId);

    void deleteAnimeInWatchingList(UUID userId, Integer animeId);

    void deleteAnimeInFinishedList(UUID userId, Integer animeId);

    void deleteAnimeInDroppedList(UUID userId, Integer animeId);
}
