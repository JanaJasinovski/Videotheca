package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.entities.Actor;
import org.example.services.ActorService;

import java.io.IOException;
import java.time.LocalDate;

public class AddActorCommand implements Command {
    private final ActorService actorService = ActorService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fullName = req.getParameter("fullName");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));

        Actor actor = new Actor(null, fullName, birthDate);
        actorService.addActor(actor);

        resp.sendRedirect(req.getContextPath() + "/actors");
    }
}