package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Review;
import org.example.services.ReviewService;
import org.example.util.JspHelper;
import org.example.util.UrlPath;

import java.io.IOException;
import java.util.List;

@WebServlet(UrlPath.USER_REVIEWS)
public class UserReviewsServlet extends HttpServlet {
    private final ReviewService reviewService = ReviewService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        List<Review> reviews = reviewService.findReviewsByUserId(userId); // Этот метод нужно реализовать

        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher(JspHelper.getPath("userReviews"))
                .forward(req, resp);
    }
}