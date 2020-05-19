package com.example.book.service;

import com.example.book.controller.objects.CreateBookInput;
import com.example.book.controller.objects.UpdateBookInput;
import com.example.book.data.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Book addBook(CreateBookInput createBookInput);

    Book updateBook(UpdateBookInput updateBookInput, Long isbn);

    Book getBook(Long isbn);

    String deleteBook(Long isbn);

    List<Book> getAllBooks();
}
