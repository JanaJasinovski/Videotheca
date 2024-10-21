package org.example.dao;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.entities.Review;
import org.example.util.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ReviewDao implements Dao<Integer, Review> {

    private static final ReviewDao INSTANCE = new ReviewDao();

    private static final String FIND_REVIEWS_BY_FILM_ID = """
            SELECT id, filmId, userId, text, rating
            FROM review
            WHERE filmId = ?
            """;

    private static final String FIND_REVIEWS_BY_USER_ID = """
            SELECT id, filmId, userId, text, rating
            FROM review
            WHERE userId = ?
            """;
    private static final String SAVE ="""
             INSERT INTO review (filmId, userId, text, rating) VALUES (?, ?, ?, ?)
             RETURNING id
         """;

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public Review save(Review entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, entity.getFilmId());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setInt(4, entity.getRating());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> getFilmsByYear(int year) {
        return null;
    }

    @Override
    public List<Review> findActorsByFilmId(Long filmId) {
        return null;
    }

    @SneakyThrows
    public List<Review> findReviewsByFilmId(Integer filmId) {
        List<Review> reviews = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_REVIEWS_BY_FILM_ID)) {
            preparedStatement.setInt(1, filmId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reviews.add(new Review(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("filmId", Integer.class),
                        resultSet.getObject("userId", Integer.class),
                        resultSet.getObject("text", String.class),
                        resultSet.getObject("rating", Integer.class)
                ));
            }
        }
        return reviews;
    }

    @SneakyThrows
    public List<Review> findReviewsByUserId(Integer userId) {
        List<Review> reviews = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_REVIEWS_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reviews.add(new Review(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("filmId", Integer.class),
                        resultSet.getObject("userId", Integer.class),
                        resultSet.getObject("text", String.class),
                        resultSet.getObject("rating", Integer.class)
                ));
            }
        }
        return reviews;
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
