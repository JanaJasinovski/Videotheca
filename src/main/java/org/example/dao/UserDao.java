package org.example.dao;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.entities.User;
import org.example.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL =
            "INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)";

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE email = ? AND password = ?";

    private static final String FIND_ALL_USERS = """
            SELECT id, fullname
            FROM users
            """;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getFullName());
            preparedStatement.setObject(2, entity.getEmail());
            preparedStatement.setObject(3, entity.getPassword());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getFilmsByYear(int year) {
        return null;
    }

    @Override
    public List<User> findActorsByFilmId(Long filmId) {
        return null;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return Optional.ofNullable(user);
        }
    }

    @SneakyThrows
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("fullname", String.class)
                ));
            }
        }
        return users;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private User buildEntity(ResultSet resultSet) throws java.sql.SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .fullName(resultSet.getObject("fullname", String.class))
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .build();
    }
}
