package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.example.entities.keys.FilmActorId;

@Data
@Entity
@Table(name = "film_actor")
@IdClass(FilmActorId.class)
public class FilmActor {

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;
}
