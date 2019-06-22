package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.repository.BookRepository;
import com.lambdaschool.starthere.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository repo;

    @Override
    public void save(Review review) {
        repo.save(review);
    }
}
