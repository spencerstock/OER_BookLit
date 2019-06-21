package com.lambdaschool.starthere.controllers;



        import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
        import com.lambdaschool.starthere.models.Book;
        import com.lambdaschool.starthere.services.AuthorService;
        import com.lambdaschool.starthere.services.BookService;
        import io.swagger.annotations.*;
        import org.springframework.data.web.PageableDefault;
        import org.slf4j.Logger;
        import com.lambdaschool.starthere.models.ErrorDetail;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Pageable;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BooksController {

    private static final Logger logger = LoggerFactory.getLogger(Book.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorsService;

    @ApiOperation(value = "return a list of courses, supports pagination", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "specifies the page that you want to access"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "specifies the page size"),
            @ApiImplicitParam(name = "sort", dataType = "string", allowMultiple = true, paramType = "query", value = "Sorts result [name, address, etc]")
    })
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 10) Pageable pageable)
    {

        List<Book> myBooks = bookService.findAll(pageable);

        if (myBooks == null) {
            throw new ResourceNotFoundException("no students found");
        } else{
            logger.info("/courses accessed");
        }

        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }



    @ApiOperation(value = "updates a book on the database", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "book successfully updated", response = Book.class),
            @ApiResponse(code = 500, message = "failed to update book", response = ErrorDetail.class)
    })
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book updateBook, @PathVariable long id) {

        bookService.updateBook(updateBook, id);

        if (bookService.updateBook(updateBook, id) == null) {
            throw new ResourceNotFoundException("could not update book");
        } else{
            logger.info("/data/books/{id} endpoint accessed");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "deletes a Book from the database", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book successfully deleted", response = Book.class),
            @ApiResponse(code = 500, message = "failed to delete Book", response = ErrorDetail.class)
    })
    @DeleteMapping("/data/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable long id) {

        if (bookService.findBookById(id) == null) {
            throw new ResourceNotFoundException("could not find book with id of " + id);
        } else{
            logger.info("/data/books/{id} endpoint accessed");
            bookService.delete(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "deletes a Book from the database", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Book succefully assigned to author", response = Book.class),
            @ApiResponse(code = 500, message = "failed to find Author or Book", response = ErrorDetail.class)
    })
    @PostMapping("/data/author/{authorid}/book/{bookid}")
    public ResponseEntity<?> assignBooktoAuthor(@PathVariable long authorid, @PathVariable long bookid) {

        if (authorsService.findAuthorById(authorid) == null) {
            throw new ResourceNotFoundException("could not find author with id of " + authorid);
        } else{
            logger.info("/data/books/authors{id} endpoint accessed");
            bookService.assignBooktoAuthor(authorid, bookid);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}