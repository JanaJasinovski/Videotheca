package org.example.services;

import lombok.NoArgsConstructor;
import org.example.dao.DirectorDao;
import org.example.entities.Director;

import java.util.List;

@NoArgsConstructor
public class DirectorService {
    private static final DirectorService INSTANCE = new DirectorService();
    private final DirectorDao directorDao = DirectorDao.getInstance();

    public List<Director> findAll() {
        return directorDao.findAll();
    }

    public static DirectorService getInstance() {
        return INSTANCE;
    }

    public void addDirector(Director director) {
        directorDao.save(director);
    }

    public Director findByFullName(String fullName) {
        return directorDao.findByFullName(fullName);
    }
}
