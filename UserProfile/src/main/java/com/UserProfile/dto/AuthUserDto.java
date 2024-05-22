package com.UserProfile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDto {

    @NotBlank(message = "The userId can not be empty, register or login again.")
    private UUID userId; // The userId must be created in userdb, and it can only be provided from auth service.

    @NotBlank(message = "The username can't be blank!")
    private String username;
}
