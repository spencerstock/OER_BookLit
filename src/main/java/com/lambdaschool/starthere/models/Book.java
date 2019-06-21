package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @Column(unique = true)
    private String isbn;
    @Column
    private int copy; //the year the book was copyrighted

    @Column(nullable = false)
    private String booktitle;


    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private List<BookAuthors> bookAuthors = new ArrayList<>();


    public Book()
    {
    }


    public Book(String isbn, int copy, String booktitle) {
        this.isbn = isbn;
        this.copy = copy;
        this.booktitle = booktitle;
    }

    public long getBookid() {
        return bookid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }
}