package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repo;

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(bookList::add);
        return bookList;
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> bookList = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(bookList::add);
        return bookList;
    }

    @Transactional
    @Override
    public Book updateBook(Book book, long id) {
        Book currentBook = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        if(book.getBooktitle() != null){
            currentBook.setBooktitle(book.getBooktitle());
        }
        if (book.getAuthors() != null && book.getAuthors().size() > 0){
            currentBook.setAuthors(book.getAuthors());
        }
        if(book.getCopy() != -1){
            currentBook.setCopy(book.getCopy());
        }
        if (book.getIsbn() != null){
            currentBook.setIsbn(book.getIsbn());
        }

        repo.save(currentBook);
        return currentBook;
    }


    @Transactional
    @Override
    public void assignAuthor(long bookid, long authorid) {
        Book currentBook = repo.findById(bookid).orElseThrow(EntityNotFoundException::new);
        currentBook.getAuthors().add(authorRepo.findById(authorid).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (repo.findById(id).isPresent()){
            repo.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }

    }

    @Override
    public void save(Book book) {
        repo.save(book);
    }

    @Override
    public Book findBookById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}