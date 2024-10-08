package org.example.services;

import lombok.NoArgsConstructor;
import org.example.dao.ActorDao;
import org.example.entities.Actor;

import java.util.List;

@NoArgsConstructor
public class ActorService {
    private static final ActorService INSTANCE = new ActorService();

    private final ActorDao actorDao = ActorDao.getInstance();

    public List<Actor> findActorsByFilmId(Long filmId) {
        return actorDao.findActorsByFilmId(filmId);
    }

    public static ActorService getInstance() {
        return INSTANCE;
    }

    public List<Actor> findAll() {
        return actorDao.findAll();
    }
}
