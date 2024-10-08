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

@WebServlet(UrlPath.ACTORS)
public class ActorServlet extends HttpServlet {
    private final ActorService actorService = ActorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Actor> actors = actorService.findAll();

        req.setAttribute("actors", actors);
        req.getRequestDispatcher(JspHelper.getPath("actors"))
                .forward(req, resp);
    }
}
