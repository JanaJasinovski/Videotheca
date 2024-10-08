package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private Integer id;
    private String name;
    private Set<Integer> actorsId;
    private Integer directorId;
    private LocalDateTime releaseDate;
    private String country;
    private String genre;

    public Film(Integer id, String name, Integer directorId, LocalDateTime releaseDate, String country, String genre) {
        this.id = id;
        this.name = name;
        this.directorId = directorId;
        this.releaseDate = releaseDate;
        this.country = country;
        this.genre = genre;
    }
}
