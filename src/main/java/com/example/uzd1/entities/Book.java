package com.example.uzd1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title")
    private String title;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="author_id", nullable=false,
            foreignKey = @ForeignKey(name = "fk_book_author"))
    private Author author;



    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_book_genre_book")),
            inverseJoinColumns = @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "fk_book_genre_genre"))
    )
    private List<Genre> genres;


    public Book( ){

    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public void setAuthor(Author author) {
        this.author = author;
    }


}
