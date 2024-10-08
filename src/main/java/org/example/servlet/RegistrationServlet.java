package org.example.servlet;

import org.example.dto.CreateUserDto;
import org.example.exception.ValidationException;
import org.example.services.UserService;
import org.example.util.JspHelper;
import org.example.util.UrlPath;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = UrlPath.REGISTRATION, name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = CreateUserDto.builder()
                .fullName(req.getParameter("fullName"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        try {
            userService.create(userDto);
            resp.sendRedirect(req.getContextPath() + UrlPath.LOGIN);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
