package org.example.dao;

import lombok.NoArgsConstructor;
import org.example.entities.Actor;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ActorDao implements Dao<Long, Actor> {

    private static final ActorDao INSTANCE = new ActorDao();

    @Override
    public List<Actor> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Actor", Actor.class).list();
        }
    }


    @Override
    public Actor save(Actor actor) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
            return actor;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Actor> findActorsByFilmId(Long filmId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("""
                            SELECT a FROM Actor a
                            JOIN FilmActor fa ON a.id = fa.actor.id
                            WHERE fa.film.id = :filmId
                            """, Actor.class)
                    .setParameter("filmId", filmId)
                    .list();
        }
    }

    public List<Actor> findByFullName(String fullName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Actor WHERE fullName = :fullName", Actor.class)
                    .setParameter("fullName", fullName)
                    .getResultList();
        }
    }

    public static ActorDao getInstance() {
        return INSTANCE;
    }
}
