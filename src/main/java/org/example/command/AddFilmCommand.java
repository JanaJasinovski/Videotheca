package org.example.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.dto.FilmDto;
import org.example.entities.Actor;
import org.example.entities.Director;
import org.example.services.ActorService;
import org.example.services.DirectorService;
import org.example.services.FilmService;
import org.example.util.JspHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AddFilmCommand implements Command {
    private final ActorService actorService = ActorService.getInstance();
    private final DirectorService directorService = DirectorService.getInstance();
    private final FilmService filmService = FilmService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String name = req.getParameter("name");
        String directorFullName = req.getParameter("directorFullName");
        String[] actorsNames = req.getParameter("actorsNames").split(",");
        String releaseDate = req.getParameter("releaseDate");
        String country = req.getParameter("country");
        String genre = req.getParameter("genre");

        // Находим режиссёра по имени
        Director director = directorService.findByFullName(directorFullName);
        if (director == null) {
            req.setAttribute("error", "Режиссёр не найден");
            req.getRequestDispatcher(JspHelper.getPath("addFilm")).forward(req, resp);
            return;
        }

        Set<List<Actor>> actors = Arrays.stream(actorsNames)
                .map(String::trim)
                .map(actorService::findByFullName)
                .filter(actor -> actor != null)
                .collect(Collectors.toSet());

        if (actors.isEmpty()) {
            req.setAttribute("error", "Актёры не найдены");
            req.getRequestDispatcher(JspHelper.getPath("addFilm")).forward(req, resp);
            return;
        }

        Set<Actor> uniqueActors = actors.stream()
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        LocalDate releaseDateParsed = LocalDate.parse(releaseDate);

        FilmDto filmDto = FilmDto.builder()
                .name(name)
                .director(director)
                .actors(uniqueActors)
                .releaseDate(releaseDateParsed.atStartOfDay())  // LocalDate в LocalDateTime
                .country(country)
                .genre(genre)
                .build();

        filmService.addFilm(filmDto);

        req.setAttribute("message", "Фильм успешно добавлен");
        resp.sendRedirect(req.getContextPath() + "/films");
    }
}
