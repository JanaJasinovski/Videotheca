package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.entities.Director;
import org.example.services.DirectorService;

import java.io.IOException;
import java.time.LocalDate;

public class AddDirectorCommand implements Command {
    private final DirectorService directorService = DirectorService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fullName = req.getParameter("fullName");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));

        Director director = new Director(null, fullName, birthDate);
        directorService.addDirector(director);

        resp.sendRedirect(req.getContextPath() + "/directors");
    }
}