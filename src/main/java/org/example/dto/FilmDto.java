package org.example.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@Builder
public class FilmDto {
    Integer id;
    String name;
    Set<Integer> actorsId;
    Integer directorId;
    LocalDateTime releaseDate;
    String country;
    String genre;
}
