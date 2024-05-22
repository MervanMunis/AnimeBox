package com.UserProfile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserInfoDto {

    @NotNull(message = "The userId cannot be null")
    private UUID userId;

    @NotNull(message = "The username cannot be null")
    private String username;

    private Integer age;
    private String country;
    private String instagramLink;
    private String image;
}
