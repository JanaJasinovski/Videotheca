package org.example.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.dto.CreateUserDto;
import org.example.exception.ValidationException;
import org.example.services.UserService;
import org.example.util.UrlPath;

import java.io.IOException;

public class RegistrationCommand implements Command {
    private final UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
