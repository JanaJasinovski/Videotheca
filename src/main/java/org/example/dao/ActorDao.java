package org.example.dao;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.entities.Actor;
import org.example.entities.User;
import org.example.util.ConnectionManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = PRIVATE)
public class ActorDao implements Dao<Long, Actor> {

    private static final ActorDao INSTANCE = new ActorDao();

    private static final String SAVE_SQL =
            "INSERT INTO actors (fullName, birthDate) VALUES (?, ?)";

    private static final String FIND_ACTORS_BY_FILM_ID = """
            SELECT a.id, a.fullName, a.birthdate
            FROM actor a
            JOIN film_actor fa ON a.id = fa.actor_id
            WHERE fa.film_id = ?
            """;

    private static final String FIND_ALL_ACTORS = """
            SELECT fullName, birthdate
            FROM actor 
            """;

    @Override
    public List<Actor> findAll() {
        List<Actor> actors = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_ACTORS)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actors.add(new Actor(
                        resultSet.getObject("fullName", String.class),
                        resultSet.getObject("birthdate", LocalDate.class)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actors;
    }


    @Override
    @SneakyThrows
    public Actor save(Actor actor) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, actor.getFullName());
            preparedStatement.setObject(2, actor.getBirthDate());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                actor.setId(generatedKeys.getInt(1));
            }
            return actor;
        }
    }

    @Override
    public List<Actor> getFilmsByYear(int year) {
        return null;
    }

    @Override
    @SneakyThrows
    public List<Actor> findActorsByFilmId(Long filmId) {
        List<Actor> actors = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ACTORS_BY_FILM_ID)) {
            preparedStatement.setLong(1, filmId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actors.add(new Actor(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("fullName", String.class),
                        resultSet.getObject("birthdate", LocalDate.class)
                ));
            }
        }
        return actors;
    }

    public static ActorDao getInstance() {
        return INSTANCE;
    }
}
