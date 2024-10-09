package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.util.UrlPath;

import java.io.IOException;

public class LocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        var language = req.getParameter("lang");
        req.getSession().setAttribute("lang", language);

        var prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : UrlPath.LOGIN;

        try {
            resp.sendRedirect(page + "?lang=" + language);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
