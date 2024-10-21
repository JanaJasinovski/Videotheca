package org.example.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "film_actors", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "actor_id")
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
