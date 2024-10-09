package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.dto.UserDto;
import org.example.services.UserService;
import org.example.util.UrlPath;

import java.io.IOException;

public class LoginCommand implements Command {
    private final UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        userService.login(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(
                        user -> {
                            try {
                                onLoginSuccess(user, req, resp);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> {
                            try {
                                onLoginFail(req, resp);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }

    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + UrlPath.LOGIN + "?error&email=" + req.getParameter("email"));
    }

    private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + UrlPath.FILMS);
    }
}
