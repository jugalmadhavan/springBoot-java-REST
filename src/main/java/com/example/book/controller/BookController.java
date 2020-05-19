package com.example.book.controller;

import com.example.book.controller.DTO.BookDTO;
import com.example.book.model.Book;
import com.example.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{version}")
public class BookController {

    static final String VERSION_NOT_SUPPORTED = "This API version is not supported";
    static final String BAD_REQUEST_MSG = "Invalid input";

    private enum ApiVersion {
        Version1("v1");

        private String version;

        ApiVersion(String version) {
            this.version = version;
        }

        public String geVersion() {
            return version;
        }
    }

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO, @PathVariable String version) {

        ResponseEntity responseEntity;

        if (checkValidInput(bookDTO)) {
            responseEntity = new ResponseEntity(BAD_REQUEST_MSG, HttpStatus.BAD_REQUEST);
        } else {
            if (checkVersion(version)) {
                responseEntity = new ResponseEntity<>(bookService.addBook(bookDTO), HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
            }
        }
        return responseEntity;

    }

    private boolean checkValidInput(BookDTO bookDTO) {
        return bookDTO.getISBN() == null || bookDTO.getName() == null ||
                bookDTO.getDescription() == null || bookDTO.getLanguage() == null ||
                bookDTO.getAuthor() == null || bookDTO.getPrice() == 0;
    }

    @PutMapping("/books/{isbn}")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO, @PathVariable String version, @PathVariable Long isbn) {

        ResponseEntity<Book> responseEntity;

        if (checkVersion(version)) {
            responseEntity = new ResponseEntity<>(bookService.updateBook(bookDTO, isbn), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

    @GetMapping("/books/{isbn}")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<Book> getBook(@PathVariable String version, @PathVariable Long isbn) {

        ResponseEntity responseEntity;

        if (checkVersion(version)) {
            Book book = bookService.getBook(isbn);
            if (book == null) {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                responseEntity = new ResponseEntity<>(bookService.getBook(isbn), HttpStatus.OK);
            }

        } else {
            responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

    @DeleteMapping("/books/{isbn}")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable String version, @PathVariable Long isbn) {

        ResponseEntity<String> responseEntity;

        if (checkVersion(version)) {
            responseEntity = new ResponseEntity<>(bookService.deleteBook(isbn), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

    @GetMapping("/books")
//    @PreAuthorize("hasAnyAuthority('SUPPORT','ADMIN')")
    public ResponseEntity<List<Book>> getAllBooks(@PathVariable String version, @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {

        ResponseEntity<List<Book>> responseEntity;

        if (checkVersion(version)) {
            responseEntity = new ResponseEntity<>(bookService.getAllBooks(page,size), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(VERSION_NOT_SUPPORTED, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return responseEntity;

    }

    private boolean checkVersion(String version) {
        return version.equalsIgnoreCase(ApiVersion.Version1.geVersion());
    }

}
