package com.example.book.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "t_books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @JsonIgnore
    private Long bookID = 0L;
    @Column(name = "isbn")
    private Long ISBN;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Enumerated(STRING)
    @Column(name = "language")
    private Language language = Language.GERMAN;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    private int price;

    public enum Language {
        GERMAN,
        ENGLISH
    }

    public Book() {
    }

    public Book(Long ISBN, String name, String description, Language language, String author, int price) {
        this.ISBN = ISBN;
        this.name = name;
        this.description = description;
        this.language = language;
        this.author = author;
        this.price = price;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}