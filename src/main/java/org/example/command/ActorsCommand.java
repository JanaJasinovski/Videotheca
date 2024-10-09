package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.entities.Actor;
import org.example.services.ActorService;
import org.example.util.JspHelper;

import java.io.IOException;
import java.util.List;

public class ActorsCommand implements Command {
    private final ActorService actorService = ActorService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Actor> actors = actorService.findAll();
        req.setAttribute("actors", actors);

        try {
            req.getRequestDispatcher(JspHelper.getPath("actors")).forward(req, resp);
        } catch (Exception e) {
            throw new IOException("Failed to forward to actors JSP", e);
        }
    }
}