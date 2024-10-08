package org.example.services;

import lombok.NoArgsConstructor;
import org.example.dao.ActorDao;
import org.example.dao.FilmDao;
import org.example.dto.FilmDto;
import org.example.entities.Actor;

import java.util.List;

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
                        .actorsId(film.getActorsId())
                        .directorId(film.getDirectorId())
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
                        .actorsId(film.getActorsId())
                        .directorId(film.getDirectorId())
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
                        .directorId(film.getDirectorId())
                        .releaseDate(film.getReleaseDate())
                        .country(film.getCountry())
                        .genre(film.getGenre())
                        .build()
                )
                .collect(toList());
    }

    public static FilmService getInstance() {
        return INSTANCE;
    }
}
