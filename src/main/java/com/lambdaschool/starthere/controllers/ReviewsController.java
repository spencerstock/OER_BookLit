package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.services.BookService;
import com.lambdaschool.starthere.services.ReviewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewsController {

    private static final Logger logger = LoggerFactory.getLogger(Review.class);

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;


    @ApiOperation(value = "adds a review to a book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "review successfully added", response = Review.class),
            @ApiResponse(code = 500, message = "failed to add review", response = ErrorDetail.class)
    })
    @PutMapping(value = "/addreview/book/{id}")
    public ResponseEntity<?> postReview(@RequestBody Review review, @PathVariable long id) {


        if (!bookService.addReview(review, id)) {
            throw new ResourceNotFoundException("could not add review");
        }
        logger.info("/data/books/{id} UPDATE endpoint accessed");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
