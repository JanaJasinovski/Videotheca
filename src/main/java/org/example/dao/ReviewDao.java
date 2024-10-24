package org.example.dao;

import lombok.NoArgsConstructor;
import org.example.entities.Review;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ReviewDao implements Dao<Integer, Review> {

    private static final ReviewDao INSTANCE = new ReviewDao();

    @Override
    public List<Review> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Review> query = session.createQuery("FROM Review", Review.class);
            return query.getResultList();
        }
    }

    @Override
    public Review save(Review entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public List<Review> findReviewsByFilmId(Integer filmId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Review> query = session.createQuery("FROM Review r WHERE r.film.id = :filmId", Review.class);
            query.setParameter("filmId", filmId);
            return query.getResultList();
        }
    }

    public List<Review> findReviewsByUserId(Integer userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Review> query = session.createQuery("FROM Review r WHERE r.user.id = :userId", Review.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        }
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
