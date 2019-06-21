package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
public class Author extends Auditable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @Column
    private String lastname;
    @Column
    private String firstname;


    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("author")
    private List<BookAuthors> bookAuthors = new ArrayList<>();
}

