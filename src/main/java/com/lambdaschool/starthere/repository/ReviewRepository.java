package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
