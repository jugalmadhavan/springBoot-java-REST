package com.example.book.service.impl;

import com.example.book.controller.objects.CreateBookInput;
import com.example.book.controller.objects.UpdateBookInput;
import com.example.book.data.Book;
import com.example.book.repository.BookRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {


    private final BookRepository bookRepository = mock(BookRepository.class);
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl(bookRepository);

    @Nested
    class TestAddingBooks {
        @Test
        public void addBook_WhenValidInputs_ShouldCreateAndReturnNewBook() {
            CreateBookInput createBookInput = new CreateBookInput(9783426435359L, "Book title", "Book description", Book.Language.GERMAN, "Author", 100);

            when(bookRepository.save(Mockito.any(Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);
            // ---
            Book result = bookServiceImpl.addBook(createBookInput);
            // ---
            assertNotNull(result);
            assertEquals(9783426435359L, result.getISBN());
        }

        @Test
        public void updateBook_WhenValidISBN_ShouldUpdateAndReturnUpdatedBook() {
            UpdateBookInput updateBookInput = new UpdateBookInput("Update Book title", "Book description", Book.Language.GERMAN, "Author", 100);
            Long isbn = 9783426435359L;
            Book book = new Book(9783426435359L, "Old Book title", "Book description", Book.Language.GERMAN, "Author", 100);

            when(bookRepository.findByISBN(isbn)).thenReturn(book);

            when(bookRepository.save(Mockito.any(Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);
            // ---
            Book result = bookServiceImpl.updateBook(updateBookInput, isbn);
            // ---
            assertNotNull(result);
            assertEquals(updateBookInput.getName(), result.getName());
        }

        @Test
        public void getBook_WhenValidISBN_ShouldReturnBook() {
            Long isbn = 9783426435359L;
            Book book = new Book(9783426435359L, "Book title", "Book description", Book.Language.GERMAN, "Author", 100);

            when(bookRepository.findByISBN(isbn)).thenReturn(book);

            // ---
            Book result = bookServiceImpl.getBook(isbn);
            // ---
            assertNotNull(result);
            assertEquals(book.getName(), result.getName());
        }

        @Test
        public void deleteBook_WhenValidISBN_ShouldReturnSuccessMsg() {
            Long isbn = 9783426435359L;
            when(bookRepository.deleteByISBN(isbn)).thenReturn(1L);

            // ---
            String message = bookServiceImpl.deleteBook(isbn);
            // ---
            assertNotNull(message);
            assertThat(message, containsString("Book successfully deleted"));
        }

        @Test
        public void deleteBook_WhenInvalidISBN_ShouldReturnErrorMsg() {
            Long isbn = 9783426435359L;
            when(bookRepository.deleteByISBN(isbn)).thenReturn(0L);

            // ---
            String message = bookServiceImpl.deleteBook(isbn);
            // ---
            assertNotNull(message);
            assertThat(message, containsString("Book is not existing"));
        }

        @Test
        public void getAllBooks_WhenValidISBN_ShouldReturnBook() {
            ArrayList<Book> bookList = new ArrayList<Book>();
            bookList.add(new Book(9783426435359L, "Book title", "Book description", Book.Language.GERMAN, "Author", 100));
            bookList.add(new Book(232435359L, "Second Book title", "Book description", Book.Language.ENGLISH, "Author", 100));

            when(bookRepository.findAll()).thenReturn(bookList);

            // ---
            List<Book> result = bookServiceImpl.getAllBooks();
            // ---
            assertNotNull(result);
            assertEquals(2, result.size());
        }

    }

}
