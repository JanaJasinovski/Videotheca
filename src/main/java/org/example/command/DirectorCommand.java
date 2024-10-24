package org.example.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.entities.Director;
import org.example.services.DirectorService;
import org.example.util.JspHelper;

import java.io.IOException;
import java.util.List;

public class DirectorCommand implements Command {
    private final DirectorService directorService = DirectorService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Director> directors = directorService.findAll();
        request.setAttribute("directors", directors);

        try {
            request.getRequestDispatcher(JspHelper.getPath("directors")).forward(request, response);
        } catch (Exception e) {
            throw new IOException("Failed to forward to directors JSP", e);
        }
    }
}
