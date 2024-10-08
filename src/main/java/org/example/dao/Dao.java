package org.example.dao;

import java.util.List;

public interface Dao<K, T> {
    List<T> findAll();

    T save(T entity);

    List<T> getFilmsByYear(int year);

    List<T> findActorsByFilmId(Long filmId);
}