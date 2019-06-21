package com.lambdaschool.starthere.services;


import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> findAll(Pageable pageable);
    List<Book> findAll();

    Book updateBook(Book book, long id);

    void assignAuthor(long bookid, long authorid);

    void delete(long id);

    Book findBookById(long id);

    void assignBooktoAuthor(long authorid, long bookid);
}