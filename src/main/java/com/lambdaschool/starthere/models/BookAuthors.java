package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bookauthors")
public class BookAuthors extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties("bookAuthors")
    private Author author;

    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties("bookAuthors")
    private Book book;

    public BookAuthors() {
    }

    public BookAuthors(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookAuthors)) {
            return false;
        }
        BookAuthors bookAuthors = (BookAuthors) o;
        return getAuthor().equals(bookAuthors.getAuthor()) && getBook().equals(bookAuthors.getBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getBook());
    }
}