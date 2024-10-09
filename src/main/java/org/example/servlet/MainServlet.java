package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.command.ActorsCommand;
import org.example.command.DownloadCommand;
import org.example.command.FilmActorsCommand;
import org.example.command.FilmsCommand;
import org.example.command.LocaleCommand;
import org.example.command.LogoutCommand;
import org.example.command.RegistrationCommand;
import org.example.command.ReviewsCommand;
import org.example.command.UserReviewsCommand;
import org.example.util.UrlPath;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private final Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        commandMap.put(UrlPath.FILMS, new FilmsCommand());
        commandMap.put(UrlPath.ACTORS, new ActorsCommand());
        commandMap.put(UrlPath.LOGOUT, new LogoutCommand());
        commandMap.put(UrlPath.REVIEWS, new ReviewsCommand());
        commandMap.put(UrlPath.FILM_ACTORS, new FilmActorsCommand());
        commandMap.put(UrlPath.USER_REVIEWS, new UserReviewsCommand());
        commandMap.put(UrlPath.LOCALE, new LocaleCommand());
        commandMap.put(UrlPath.DOWNLOAD, new DownloadCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getServletPath();
        Command command = commandMap.get(commandName);

        if (command != null) {
            try {
                command.execute(req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}