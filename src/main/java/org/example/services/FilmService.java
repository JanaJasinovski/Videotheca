package org.example.services;

import lombok.NoArgsConstructor;
import org.example.dao.ActorDao;
import org.example.dao.FilmDao;
import org.example.dto.FilmDto;
import org.example.entities.Actor;
import org.example.entities.Film;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
public class FilmService {
    private static final FilmService INSTANCE = new FilmService();

    private final FilmDao filmsDao = FilmDao.getInstance();

    public List<FilmDto> findAll() {
        return filmsDao.findAll().stream()
                .map(film -> FilmDto.builder()
                        .id(film.getId())
                        .name(film.getName())
                        .actors(film.getActors())
                        .director(film.getDirector())
                        .releaseDate(film.getReleaseDate())
                        .country(film.getCountry())
                        .genre(film.getGenre())
                        .build()
                )
                .collect(toList());
    }

    public List<FilmDto> findByYear(int year) {
        return filmsDao.getFilmsByYear(year).stream()
                .map(film -> FilmDto.builder()
                        .id(film.getId())
                        .name(film.getName())
                        .actors(film.getActors())
                        .director(film.getDirector())
                        .releaseDate(film.getReleaseDate())
                        .country(film.getCountry())
                        .genre(film.getGenre())
                        .build()
                )
                .collect(toList());
    }

    public List<FilmDto> findFilmsByActorName(String actorName) {
        return filmsDao.findFilmsByActorName(actorName).stream()
                .map(film -> FilmDto.builder()
                        .id(film.getId())
                        .name(film.getName())
                        .director(film.getDirector())
                        .releaseDate(film.getReleaseDate())
                        .country(film.getCountry())
                        .genre(film.getGenre())
                        .build()
                )
                .collect(toList());
    }

    public void addFilm(FilmDto film) {
        filmsDao.addFilm(film);
    }

    public Film getById(Integer id) {
        return filmsDao.getById(id);
    }

    public static FilmService getInstance() {
        return INSTANCE;
    }

}
