package org.example.entities.keys;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmActorId implements Serializable {
    private Integer film;
    private Integer actor;
}
