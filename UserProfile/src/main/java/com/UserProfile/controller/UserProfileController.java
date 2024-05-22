package com.UserProfile.controller;

import com.UserProfile.dto.AuthUserDto;
import com.UserProfile.dto.UserInfoDto;
import com.UserProfile.dto.UserProfileDto;
import com.UserProfile.service.UserProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createUserProfile(@RequestBody AuthUserDto authUserDto) {
        String user = userProfileService.createUserProfile(authUserDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDto> getUserProfileByUserId(@PathVariable("userId") UUID userId){
        UserProfileDto userProfileDto = userProfileService.getUserProfileByUserId(userId);
        return ResponseEntity.ok(userProfileDto);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserProfileDto>> getAllUserProfiles(){
        List<UserProfileDto> userProfileDtoList = userProfileService.getAllUserProfiles();
        return ResponseEntity.ok(userProfileDtoList);
    }

    @PutMapping("/updateWatchList/{userId}/{animeId}")
    public ResponseEntity<String> UpdateAnimeInWatchList(@PathVariable("userId") UUID userId,
                                                         @PathVariable("animeId") Integer animeId) {
        userProfileService.updateAnimeInWatchList(userId, animeId);
        return ResponseEntity.ok("The Watch List Is Updated.");
    }

    @PutMapping("/updateWatchingList/{userId}/{animeId}")
    public ResponseEntity<String> UpdateAnimeInWatchingList(@PathVariable("userId") UUID userId,
                                                            @PathVariable("animeId") Integer animeId) {
        userProfileService.updateAnimeInWatchingList(userId, animeId);
        return ResponseEntity.ok("The Watching List Is Updated.");
    }

    @PutMapping("/updateFinishedList/{userId}/{animeId}")
    public ResponseEntity<String> UpdateAnimeInFinishedList(@PathVariable("userId") UUID userId,
                                                            @PathVariable("animeId") Integer animeId){
        userProfileService.updateAnimeInFinishedList(userId, animeId);
        return ResponseEntity.ok("The Finished List Is Updated.");
    }

    @PutMapping("/updateDroppedList/{userId}/{animeId}")
    public ResponseEntity<String> UpdateAnimeInDroppedList(@PathVariable("userId") UUID userId,
                                                           @PathVariable("animeId") Integer animeId){
        userProfileService.updateAnimeInDroppedList(userId, animeId);
        return ResponseEntity.ok("The Dropped List Is Updated.");
    }

    @PutMapping("/updateInfo/{userId}")
    public ResponseEntity<String> updateUserInfoById(@PathVariable("userId") UUID userId,
                                                     @RequestPart MultipartFile file,
                                                     @RequestPart String userInfoDtoObj) throws IOException {

        if (file.isEmpty()) {
            file = null;
        }

        UserInfoDto userInfoDto = convertToUserInfoDto(userInfoDtoObj);
        userProfileService.updateUserInfoById(userId, userInfoDto, file);

        return ResponseEntity.ok("The User's Info Is Updated.");
    }

    @DeleteMapping("/deleteInWatchList/{userId}/{animeId}")
    public ResponseEntity<String> deleteAnimeInWatchList(@PathVariable("userId") UUID userId,
                                                         @PathVariable("animeId") Integer animeId){
        userProfileService.deleteAnimeInWatchList(userId, animeId);
        return ResponseEntity.ok("The Anime In Watch List Is Removed.");
    }

    @DeleteMapping("/deleteInWatchingList/{user}/{animeId}")
    public ResponseEntity<String> deleteAnimeInWatchingList(@PathVariable("userId") UUID userId,
                                                            @PathVariable("animeId") Integer animeId){
        userProfileService.deleteAnimeInWatchingList(userId, animeId);
        return ResponseEntity.ok("The Anime In Watching List Is Removed.");
    }

    @DeleteMapping("/deleteInFinishedList/{userId}/{animeId}")
    public ResponseEntity<String> deleteAnimeInFinishedList(@PathVariable("userId") UUID userId,
                                                            @PathVariable("animeId") Integer animeId){
        userProfileService.deleteAnimeInFinishedList(userId, animeId);
        return ResponseEntity.ok("The Anime In Finished List Is Removed.");
    }

    @DeleteMapping("/deleteInDroppedList/{userId}/{animeId}")
    public ResponseEntity<String> deleteAnimeInDroppedList(@PathVariable("userId") UUID userId,
                                                           @PathVariable("animeId") Integer animeId){
        userProfileService.deleteAnimeInDroppedList(userId, animeId);
        return ResponseEntity.ok(" The Anime In Dropped List Is Removed.");
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable("userId") UUID userId) throws IOException {
        userProfileService.deleteUserProfile(userId);
        return null;
    }

    private UserInfoDto convertToUserInfoDto(String userDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(userDtoObj, UserInfoDto.class);
    }
}




