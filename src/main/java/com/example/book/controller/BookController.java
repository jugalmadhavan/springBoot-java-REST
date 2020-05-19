package com.example.book.controller;

import com.example.book.controller.objects.CreateBookInput;
import com.example.book.controller.objects.UpdateBookInput;
import com.example.book.data.Book;
import com.example.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    static final String VERSION_NOT_SUPPORTED = "This API version is not supported";
    static final String BAD_REQUEST_MSG = "Invalid input";

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/{version}/books")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody CreateBookInput createBookInput, @PathVariable String version) {

        ResponseEntity responseEntity;

        if (checkValidInput(createBookInput)) {
            responseEntity = new ResponseEntity(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST);
        } else {
            if (version.equalsIgnoreCase("v1")) {
                responseEntity = new ResponseEntity<>(bookService.addBook(createBookInput), HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
            }
        }
        return responseEntity;

    }

    private boolean checkValidInput(@RequestBody CreateBookInput createBookInput) {
        return createBookInput.getISBN() == null || createBookInput.getName() == null ||
                createBookInput.getDescription() == null || createBookInput.getLanguage() == null ||
                createBookInput.getAuthor() == null || createBookInput.getPrice() == 0;
    }

    @PutMapping("/{version}/books/{isbn}")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<Book> updateBook(@RequestBody UpdateBookInput updateBookInput, @PathVariable String version, @PathVariable Long isbn) {

        ResponseEntity<Book> responseEntity;

        if (version.equalsIgnoreCase("v1")) {
            responseEntity = new ResponseEntity<>(bookService.updateBook(updateBookInput, isbn), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

    @GetMapping("/{version}/books/{isbn}")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<Book> getBook(@PathVariable String version, @PathVariable Long isbn) {

        ResponseEntity responseEntity;

        if (version.equalsIgnoreCase("v1")) {
            responseEntity = new ResponseEntity<>(bookService.getBook(isbn), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

    @DeleteMapping("/{version}/books/{isbn}")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable String version, @PathVariable Long isbn) {

        ResponseEntity<String> responseEntity;

        if (version.equalsIgnoreCase("v1")) {
            responseEntity = new ResponseEntity<>(bookService.deleteBook(isbn), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

    @GetMapping("/{version}/books")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable String version) {

        ResponseEntity<List<Book>> responseEntity;

        if (version.equalsIgnoreCase("v1")) {
            responseEntity = new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

}
