
CREATE TABLE actor (
                       id SERIAL PRIMARY KEY,
                       fullName VARCHAR(255) NOT NULL,
                       birthdate DATE
);

CREATE TABLE director (
                          id SERIAL PRIMARY KEY,
                          fullName VARCHAR(255) NOT NULL,
                          birthdate DATE
);


CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       fullName VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE review (
                        id SERIAL PRIMARY KEY,
                        filmId INT NOT NULL,
                        userid INT NOT NULL,
                        text TEXT NOT NULL,
                        rating INT CHECK (rating >= 1 AND rating <= 10),
                        FOREIGN KEY (filmId) REFERENCES film(id) ON DELETE CASCADE,
                        FOREIGN KEY (userid) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE film (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      directorId INT NOT NULL,
                      releaseDate TIMESTAMP NOT NULL,
                      country VARCHAR(100) NOT NULL,
                      genre VARCHAR(100) NOT NULL,
                      FOREIGN KEY (directorId) REFERENCES director(id) ON DELETE CASCADE
);
