package org.example.dao;

import lombok.NoArgsConstructor;
import org.example.entities.Director;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DirectorDao implements Dao<Integer, Director> {
    private static final DirectorDao INSTANCE = new DirectorDao();

    @Override
    public List<Director> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Director> query = session.createQuery("FROM Director", Director.class);
            return query.getResultList();
        }
    }

    @Override
    public Director save(Director director) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(director);
            transaction.commit();
            return director;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public Director findByFullName(String fullName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Director> query = session.createQuery("FROM Director WHERE fullName = :fullName", Director.class);
            query.setParameter("fullName", fullName);
            return query.uniqueResult();
        }
    }

    public static DirectorDao getInstance() {
        return INSTANCE;
    }
}
