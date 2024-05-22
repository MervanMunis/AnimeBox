package com.UserProfile.mapper;

import com.UserProfile.dto.AuthUserDto;
import com.UserProfile.dto.UserInfoDto;
import com.UserProfile.dto.UserProfileDto;
import com.UserProfile.dto.lists.DroppedListDto;
import com.UserProfile.dto.lists.FinishedListDto;
import com.UserProfile.dto.lists.WatchListDto;
import com.UserProfile.dto.lists.WatchingListDto;
import com.UserProfile.entity.*;

public class UserProfileMapper {

    public static UserProfileDto mapToUserProfileDto(UserProfile userProfile){
        return UserProfileDto.builder()
                .userId(userProfile.getUserId())
                .username(userProfile.getUsername())
                .age(userProfile.getAge())
                .country(userProfile.getCountry())
                .instagramLink(userProfile.getInstagramLink())
                .image(userProfile.getImage())
                .watchList(toWatchListDto(userProfile.getWatchList()))
                .finishedList(toFinishedListDto(userProfile.getFinishedList()))
                .watchingList(toWatchingListDto(userProfile.getWatchingList()))
                .droppedList(toDroppedListDto(userProfile.getDroppedList()))
                .build();
    }

    public static UserProfile mapToUpdateUserInfo(UserProfile userProfile, UserInfoDto userInfoDto, String file) {
        return UserProfile.builder()
                .userId(userProfile.getUserId())
                .username(userInfoDto.getUsername())
                .age(userInfoDto.getAge())
                .country(userInfoDto.getCountry())
                .instagramLink(userInfoDto.getInstagramLink())
                .image(file)
                .build();
    }

    public static UserProfile mapToUserProfile(AuthUserDto authUserDto, String fileName) {
        return UserProfile.builder()
                .userId(authUserDto.getUserId())
                .username(authUserDto.getUsername())
                .image(fileName)
                .build();
    }


    private static WatchListDto toWatchListDto(WatchList watchList) {
        if (watchList == null) {
            return null;
        }
        return WatchListDto.builder()
                .animes(watchList.getAnimes())
                .build();
    }

    private static WatchingListDto toWatchingListDto(WatchingList watchingList) {
        if (watchingList == null) {
            return null;
        }
        return WatchingListDto.builder()
                .animes(watchingList.getAnimes())
                .build();
    }

    private static FinishedListDto toFinishedListDto(FinishedList finishedList) {
        if (finishedList == null) {
            return null;
        }
        return FinishedListDto.builder()
                .animes(finishedList.getAnimes())
                .build();
    }

    private static DroppedListDto toDroppedListDto(DroppedList droppedList) {
        if (droppedList == null) {
            return null;
        }
        return DroppedListDto.builder()
                .animes(droppedList.getAnimes())
                .build();
    }
}
