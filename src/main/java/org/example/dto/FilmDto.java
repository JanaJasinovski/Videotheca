package org.example.dto;

import lombok.Builder;
import lombok.Value;
import org.example.entities.Actor;
import org.example.entities.Director;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@Builder
public class FilmDto {
    Integer id;
    String name;
    Set<Actor> actors;
    Director director;
    LocalDateTime releaseDate;
    String country;
    String genre;
}
