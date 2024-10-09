package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.entities.Actor;
import org.example.services.ActorService;
import org.example.util.JspHelper;

import java.util.List;

public class FilmActorsCommand implements Command {
    private final ActorService actorService = ActorService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Long filmId = Long.valueOf(req.getParameter("filmId"));
        List<Actor> actors = actorService.findActorsByFilmId(filmId);

        req.setAttribute("actors", actors);
        try {
            req.getRequestDispatcher(JspHelper.getPath("filmActors")).forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
