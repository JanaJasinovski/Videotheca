package org.example.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.dto.FilmDto;
import org.example.entities.User;
import org.example.services.FilmService;
import org.example.services.UserService;
import org.example.util.JspHelper;

import java.io.IOException;
import java.util.List;

public class FilmsCommand implements Command {
    private final FilmService filmService = FilmService.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String yearParam = req.getParameter("year");
        String actorNameParam = req.getParameter("actorName");

        List<FilmDto> films;

        if (actorNameParam != null && !actorNameParam.isEmpty()) {
            films = filmService.findFilmsByActorName(actorNameParam);
        } else if (yearParam != null && !yearParam.isEmpty()) {
            int year = Integer.parseInt(yearParam);
            films = filmService.findByYear(year);
        } else {
            films = filmService.findAll();
        }

        req.setAttribute("films", films);
        List<User> users = userService.findAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher(JspHelper.getPath("films"))
                .forward(req, resp);
    }
}