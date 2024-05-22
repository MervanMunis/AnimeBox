package com.UserProfile.dto;

import com.UserProfile.dto.lists.DroppedListDto;
import com.UserProfile.dto.lists.FinishedListDto;
import com.UserProfile.dto.lists.WatchListDto;
import com.UserProfile.dto.lists.WatchingListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private UUID userId; // The userId must be created in userdb, and it can only be provided from auth service.

    private String username;

    private Integer age;

    private String country;

    private String instagramLink;

    private String image;

    private WatchListDto watchList;

    private WatchingListDto watchingList;

    private FinishedListDto finishedList;

    private DroppedListDto droppedList;
}
