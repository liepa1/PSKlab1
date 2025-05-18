package com.example.uzd1.MyBatis;

public class Genre {
    private Long id;
    private String name;

    private Long bookId;

    public Genre() {
    }

    public Genre(String name, Long bookId) {
        this.name = name;
        this.bookId = bookId;
    }
    // Getteriai ir setteriai
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}