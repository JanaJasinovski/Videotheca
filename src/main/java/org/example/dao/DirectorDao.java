package org.example.dao;

import lombok.NoArgsConstructor;
import org.example.entities.Director;
import org.example.util.ConnectionManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DirectorDao implements Dao<Integer, Director> {

    private static final DirectorDao INSTANCE = new DirectorDao();

    private static final String SAVE_SQL =
            "INSERT INTO director (fullname, birthdate) VALUES (?, ?)";

    private static final String FIND_ALL_DIRECTORS = "SELECT DISTINCT id, fullname, birthdate FROM director";
    private static final String FIND_BY_FULLNAME = "SELECT * FROM director WHERE fullname = ?";

    @Override
    public List<Director> findAll() {
        List<Director> directors = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_DIRECTORS)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                directors.add(new Director(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("fullName", String.class),
                        resultSet.getObject("birthDate", LocalDate.class)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return directors;
    }

    @Override
    public Director save(Director director) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, director.getFullName());
            preparedStatement.setObject(2, director.getBirthDate());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                director.setId(generatedKeys.getInt(1));
            }
            return director;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Director> getFilmsByYear(int year) {
        return null;
    }

    @Override
    public List<Director> findActorsByFilmId(Long filmId) {
        return null;
    }

    public Director findByFullName(String fullName) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_FULLNAME)) {
            preparedStatement.setString(1, fullName);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Director(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("fullName", String.class),
                        resultSet.getObject("birthdate", LocalDate.class)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static DirectorDao getInstance() {
        return INSTANCE;
    }
}
