package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    void save (Review review);

    List<Review> findAll(Pageable pageable);

    List<Review> findReviewsByBook(Pageable pageable, Long id);
}
