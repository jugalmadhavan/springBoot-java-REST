package com.example.book.service.impl;

import com.example.book.controller.objects.CreateBookInput;
import com.example.book.controller.objects.UpdateBookInput;
import com.example.book.data.Book;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(CreateBookInput createBookInput) {

        Book newBook = new Book(
                createBookInput.getISBN(),
                createBookInput.getName(),
                createBookInput.getDescription(),
                createBookInput.getLanguage(),
                createBookInput.getAuthor(),
                createBookInput.getPrice());

        bookRepository.save(newBook);
        logger.info("New book entry added with ID: {}", newBook.getBookID());

        return newBook;

    }

    @Override
    public Book updateBook(UpdateBookInput updateBookInput, Long isbn) {

        Book updateBook = bookRepository.findByISBN(isbn);
        updateBook.setAuthor(updateBookInput.getAuthor());
        updateBook.setName(updateBookInput.getName());
        updateBook.setDescription(updateBookInput.getDescription());
        updateBook.setLanguage(updateBookInput.getLanguage());
        updateBook.setPrice(updateBookInput.getPrice());

        bookRepository.save(updateBook);
        logger.info("Book entry updated with ISBN: {}", isbn);

        return updateBook;

    }

    @Override
    public Book getBook(Long isbn) {

        return bookRepository.findByISBN(isbn);
    }

    @Override
    public String deleteBook(Long isbn) {
        String responseMsg = "Book successfully deleted with ISBN: " + isbn;

        Long delete = bookRepository.deleteByISBN(isbn);

        if (delete == 0) {
            responseMsg = "Book is not existing with ISBN: " + isbn;
        }
        return responseMsg;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
