package com.UserProfile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    @NotNull(message = "The userId cannot be null")
    @Column(unique = true, nullable = false)
    private UUID userId; // The userId must be created in userdb, and it can only be provided from auth service.

    @NotNull(message = "The username cannot be null")
    @Column(unique = true, nullable = false)
    private String username;

    private Integer age;

    private String country;

    private String instagramLink;

    private String image;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "watch_list_id")
    private WatchList watchList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "watching_list_id")
    private WatchingList watchingList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "finished_list_id")
    private FinishedList finishedList;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dropped_list_id")
    private DroppedList droppedList;
}
