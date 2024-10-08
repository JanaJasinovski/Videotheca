package org.example.services;

import lombok.NoArgsConstructor;
import org.example.dao.ReviewDao;
import org.example.entities.Review;

import java.util.List;

@NoArgsConstructor
public class ReviewService {
    private static final ReviewService INSTANCE = new ReviewService();

    private final ReviewDao reviewDao = ReviewDao.getInstance();

    public List<Review> findReviewsByFilmId(Integer filmId) {
        return reviewDao.findReviewsByFilmId(filmId);
    }

    public static ReviewService getInstance() {
        return INSTANCE;
    }

    public List<Review> findReviewsByUserId(Integer userId) {
        return reviewDao.findReviewsByUserId(userId);
    }
}
