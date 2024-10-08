package org.example.dao;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.entities.Film;
import org.example.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FilmDao implements Dao<Long, Film> {
    private static final FilmDao INSTANCE = new FilmDao();

    private static final String FIND_ALL = """
            SELECT *
            FROM film
            """;

    private static final String FIND_BY_YEAR = """
            SELECT *
            FROM film
            WHERE EXTRACT(YEAR FROM releaseDate) = ?
            """;

    private static final String FIND_BY_ACTOR_NAME = """
            SELECT f.*
            FROM film f
            JOIN film_actor fa ON f.id = fa.film_id
            JOIN actor a ON fa.actor_id = a.id
            WHERE a.fullName = ?
            """;

    @Override
    @SneakyThrows
    public List<Film> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Film> films = new ArrayList<>();
            while (resultSet.next()) {
                films.add(buildFilm(resultSet));
            }

            return films;
        }
    }

    @Override
    public Film save(Film entity) {
        return null;
    }

    @Override
    @SneakyThrows
    public List<Film> getFilmsByYear(int year) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_YEAR)) {
            preparedStatement.setInt(1, year);
            var resultSet = preparedStatement.executeQuery();
            List<Film> films = new ArrayList<>();
            while (resultSet.next()) {
                films.add(buildFilm(resultSet));
            }
            return films;
        }
    }

    @Override
    public List<Film> findActorsByFilmId(Long filmId) {
        return null;
    }


    private Film buildFilm(ResultSet resultSet) throws SQLException {
        return new Film(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("directorId", Integer.class),
                resultSet.getObject("releaseDate", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("country", String.class),
                resultSet.getObject("genre", String.class)
        );
    }

    @SneakyThrows
    public List<Film> findFilmsByActorName(String actorName) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ACTOR_NAME)) {
            preparedStatement.setString(1, actorName);
            var resultSet = preparedStatement.executeQuery();
            List<Film> films = new ArrayList<>();
            while (resultSet.next()) {
                films.add(buildFilm(resultSet));
            }
            return films;
        }
    }

    public static FilmDao getInstance() {
        return INSTANCE;
    }

}
