package org.example.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Command;
import org.example.entities.Review;
import org.example.services.ReviewService;
import org.example.util.JspHelper;

import java.util.List;

public class UserReviewsCommand implements Command {
    private final ReviewService reviewService = ReviewService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        List<Review> reviews = reviewService.findReviewsByUserId(userId);

        req.setAttribute("reviews", reviews);
        try {
            req.getRequestDispatcher(JspHelper.getPath("userReviews")).forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
