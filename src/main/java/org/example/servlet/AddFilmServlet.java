package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.command.AddFilmCommand;
import org.example.util.JspHelper;

import java.io.IOException;


@WebServlet("/addFilm")
public class AddFilmServlet extends HttpServlet {


    private final Command loginCommand = new AddFilmCommand();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("addFilm")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            loginCommand.execute(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
