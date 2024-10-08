package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Actor;
import org.example.services.ActorService;
import org.example.util.JspHelper;
import org.example.util.UrlPath;

import java.io.IOException;
import java.util.List;

@WebServlet( UrlPath.FILM_ACTORS)
public class FilmActorsServlet extends HttpServlet {
    private final ActorService actorService = ActorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long filmId = Long.valueOf(req.getParameter("filmId"));
        List<Actor> actors = actorService.findActorsByFilmId(filmId);

        req.setAttribute("actors", actors);
        req.getRequestDispatcher(JspHelper.getPath("filmActors"))
                .forward(req, resp);
    }
}
