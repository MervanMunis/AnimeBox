package com.UserProfile.dto.lists;

import com.UserProfile.dto.AnimeDto;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinishedListDto {
    private Set<AnimeDto> animes;
}
