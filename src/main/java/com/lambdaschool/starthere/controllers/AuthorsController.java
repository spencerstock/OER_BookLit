package com.lambdaschool.starthere.controllers;

        import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
        import com.lambdaschool.starthere.models.Author;
        import com.lambdaschool.starthere.services.AuthorService;
        import io.swagger.annotations.ApiImplicitParam;
        import io.swagger.annotations.ApiImplicitParams;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.web.PageableDefault;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.RequestMapping;
        import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorsController {

    private static final Logger logger = LoggerFactory.getLogger(Author.class);

    @Autowired
    private AuthorService authorService;

    @ApiOperation(value = "return a list of courses, supports pagination", response = Author.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "specifies the page that you want to access"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "specifies the page size"),
            @ApiImplicitParam(name = "sort", dataType = "string", allowMultiple = true, paramType = "query", value = "Sorts result [name, address, etc]")
    })
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 10) Pageable pageable)
    {

        List<Author> myAuthors = authorService.findAll(pageable);

        if (myAuthors == null) {
            throw new ResourceNotFoundException("no students found");
        } else{
            logger.info("/courses accessed");
        }

        return new ResponseEntity<>(myAuthors, HttpStatus.OK);
    }

}