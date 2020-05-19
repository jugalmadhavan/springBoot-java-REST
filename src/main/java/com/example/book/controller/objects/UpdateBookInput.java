package com.example.book.controller.objects;

import com.example.book.data.Book;

public class UpdateBookInput {
    private String name;
    private String description;
    private Book.Language language;
    private String author;
    private int price;

    public UpdateBookInput(String name, String description, Book.Language language, String author, int price) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.author = author;
        this.price = price;
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

    public Book.Language getLanguage() {
        return language;
    }

    public void setLanguage(Book.Language language) {
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
