package org.example.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.dto.FilmDto;
import org.example.dto.UserDto;
import org.example.entities.Film;
import org.example.entities.Review;
import org.example.entities.User;
import org.example.services.FilmService;
import org.example.services.ReviewService;
import org.example.services.UserService;
import org.example.util.JspHelper;

import java.io.IOException;

public class AddReviewCommand implements Command {
    private final ReviewService reviewService = ReviewService.getInstance();
    private final FilmService filmService = FilmService.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer filmId = (Integer) req.getSession().getAttribute("filmId");
        UserDto user = (UserDto) req.getSession().getAttribute("user");

        String text = req.getParameter("text");
        int rating = Integer.parseInt(req.getParameter("rating"));

        Film film = filmService.getById(filmId);


        Review review = new Review(null, film, new User(user.getId(), user.getFullName(), user.getPassword(), user.getEmail()), text, rating);
        reviewService.addReview(review);

        req.setAttribute("message", "Отзыв успешно добавлен");
        req.getRequestDispatcher(JspHelper.getPath("reviews")).forward(req, resp);
    }
}
