package org.example.servlet;

import lombok.SneakyThrows;
import org.example.Command;
import org.example.command.LoginCommand;
import org.example.dto.UserDto;
import org.example.services.UserService;
import org.example.util.JspHelper;
import org.example.util.UrlPath;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(UrlPath.LOGIN)
public class LoginServlet extends HttpServlet {

    private final Command loginCommand = new LoginCommand();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
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