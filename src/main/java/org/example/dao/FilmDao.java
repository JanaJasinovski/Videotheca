package org.example.dao;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.example.dto.FilmDto;
import org.example.entities.Film;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FilmDao implements Dao<Long, Film> {
    private static final FilmDao INSTANCE = new FilmDao();

    @Override
    @SneakyThrows
    public List<Film> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("FROM Film", Film.class);
            return query.getResultList();
        }
    }

    @Override
    @SneakyThrows
    public Film save(Film entity) {
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

    @SneakyThrows
    public List<Film> getFilmsByYear(int year) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("FROM Film WHERE YEAR(releaseDate) = :year", Film.class);
            query.setParameter("year", year);
            return query.getResultList();
        }
    }

    @SneakyThrows
    public List<Film> findFilmsByActorName(String actorName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery(
                    "SELECT f FROM Film f JOIN f.actors a WHERE a.fullName = :actorName", Film.class);
            query.setParameter("actorName", actorName);
            return query.getResultList();
        }
    }

    public static FilmDao getInstance() {
        return INSTANCE;
    }

    public void addFilm(FilmDto filmDto) {
        Film film = new Film();
        film.setName(filmDto.getName());
        film.setDirector(filmDto.getDirector());
        film.setReleaseDate(filmDto.getReleaseDate());
        film.setCountry(filmDto.getCountry());
        film.setGenre(filmDto.getGenre());

        save(film);
    }

    public Film getById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Film> query = session.createQuery("FROM Film WHERE id= :id", Film.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}
