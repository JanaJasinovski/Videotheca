package org.example.dao;

import lombok.NoArgsConstructor;
import org.example.entities.Director;
import org.example.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;
@NoArgsConstructor(access = PRIVATE)
public class DirectorDao implements Dao<Integer, Director> {
    private static final String SAVE_SQL =
            "INSERT INTO director (fullname, birthdate) VALUES (?, ?, ?)";

    @Override
    public List<Director> findAll() {
      return null;
    }

    @Override
    public Director save(Director entity) {
        return null;
    }

    @Override
    public List<Director> getFilmsByYear(int year) {
        return null;
    }

    @Override
    public List<Director> findActorsByFilmId(Long filmId) {
        return null;
    }
}
