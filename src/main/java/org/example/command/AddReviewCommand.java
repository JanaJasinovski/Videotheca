package org.example.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.dto.UserDto;
import org.example.entities.Review;
import org.example.services.ReviewService;
import org.example.util.JspHelper;

import java.io.IOException;

public class AddReviewCommand implements Command {
    private final ReviewService reviewService = ReviewService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer filmId = (Integer) req.getSession().getAttribute("filmId");
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        Integer userId = user.getId();

        String text = req.getParameter("text");
        int rating = Integer.parseInt(req.getParameter("rating"));

        Review review = new Review(null, filmId, userId, text, rating);
        reviewService.addReview(review);

        req.setAttribute("message", "Отзыв успешно добавлен");
        req.getRequestDispatcher(JspHelper.getPath("reviews")).forward(req, resp);
    }
}
