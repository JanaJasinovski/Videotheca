package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.Command;
import org.example.util.UrlPath;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        invalidateSession(req);
        redirectToLogin(req, resp);
    }

    @SneakyThrows
    private void invalidateSession(HttpServletRequest req) {
        req.getSession().invalidate();
    }

    @SneakyThrows
    private void redirectToLogin(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(req.getContextPath() + UrlPath.LOGIN);
    }
}
