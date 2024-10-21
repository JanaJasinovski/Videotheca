package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.dto.FilmDto;
import org.example.services.FilmService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DownloadCommand implements Command {
    private final FilmService filmService = FilmService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Content-Disposition", "attachment; filename=\"films_report.csv\"");
        resp.setContentType("application/csv");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            List<FilmDto> films = getFilmData();

            writer.println("Название,Дата выхода,Страна,Жанр");

            for (FilmDto film : films) {
                writer.printf("%s,%s,%s,%s%n",
                        film.getName(),
                        film.getReleaseDate(),
                        film.getCountry(),
                        film.getGenre());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error during file generation", e);
        }
    }

    private List<FilmDto> getFilmData() {
        return filmService.findAll();
    }
}
