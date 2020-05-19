package com.example.book.service;

import com.example.book.controller.DTO.BookDTO;
import com.example.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Book addBook(BookDTO bookDTO);

    Book updateBook(BookDTO bookDTO, Long isbn);

    Book getBook(Long isbn);

    String deleteBook(Long isbn);

    List<Book> getAllBooks(int page, int size);
}
