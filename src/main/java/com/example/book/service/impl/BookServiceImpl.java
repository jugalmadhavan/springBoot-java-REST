package com.example.book.service.impl;

import com.example.book.controller.DTO.BookDTO;
import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Book addBook(BookDTO bookDTO) {

        Book newBook = new Book(
                bookDTO.getISBN(),
                bookDTO.getName(),
                bookDTO.getDescription(),
                bookDTO.getLanguage(),
                bookDTO.getAuthor(),
                bookDTO.getPrice());

        bookRepository.save(newBook);
        logger.info("New book entry added with ID: {}", newBook.getBookID());

        return newBook;

    }

    @Override
    public Book updateBook(BookDTO bookDTO, Long isbn) {

        Book updateBook = bookRepository.findByISBN(isbn);
        updateBook.setAuthor(bookDTO.getAuthor());
        updateBook.setName(bookDTO.getName());
        updateBook.setDescription(bookDTO.getDescription());
        updateBook.setLanguage(bookDTO.getLanguage());
        updateBook.setPrice(bookDTO.getPrice());

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

    //TODO: Add pagination and filtration
    @Override
    public List<Book> getAllBooks(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return bookRepository.findAll(paging).getContent();
    }
}
