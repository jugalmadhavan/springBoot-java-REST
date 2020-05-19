package com.example.book.service.impl;

import com.example.book.controller.DTO.BookDTO;
import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

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
            BookDTO bookDTO = new BookDTO(9783426435359L, "Book title", "Book description", Book.Language.GERMAN, "Author", 100);

            when(bookRepository.save(Mockito.any(Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);
            // ---
            Book result = bookServiceImpl.addBook(bookDTO);
            // ---
            assertNotNull(result);
            assertEquals(9783426435359L, result.getISBN());
        }

        @Test
        public void updateBook_WhenValidISBN_ShouldUpdateAndReturnUpdatedBook() {
            Long isbn = 9783426435359L;
            BookDTO bookDTO = new BookDTO(isbn,"Update Book title", "Book description", Book.Language.GERMAN, "Author", 100);

            Book book = new Book(9783426435359L, "Old Book title", "Book description", Book.Language.GERMAN, "Author", 100);

            when(bookRepository.findByISBN(isbn)).thenReturn(book);

            when(bookRepository.save(Mockito.any(Book.class)))
                    .thenAnswer(i -> i.getArguments()[0]);
            // ---
            Book result = bookServiceImpl.updateBook(bookDTO, isbn);
            // ---
            assertNotNull(result);
            assertEquals(bookDTO.getName(), result.getName());
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
            int page = 0;
            int size = 2;
            bookList.add(new Book(9783426435359L, "Book title", "Book description", Book.Language.GERMAN, "Author", 100));
            bookList.add(new Book(232435359L, "Second Book title", "Book description", Book.Language.ENGLISH, "Author", 100));
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageBook = new Page<Book>() {
                @Override
                public int getTotalPages() {
                    return 0;
                }

                @Override
                public long getTotalElements() {
                    return 0;
                }

                @Override
                public <U> Page<U> map(Function<? super Book, ? extends U> function) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return 0;
                }

                @Override
                public int getSize() {
                    return 0;
                }

                @Override
                public int getNumberOfElements() {
                    return 0;
                }

                @Override
                public List<Book> getContent() {
                    return bookList;
                }

                @Override
                public boolean hasContent() {
                    return false;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Pageable nextPageable() {
                    return null;
                }

                @Override
                public Pageable previousPageable() {
                    return null;
                }

                @Override
                public Iterator<Book> iterator() {
                    return null;
                }
            };
            when(bookRepository.findAll(paging)).thenReturn(pageBook);

            // ---
            List<Book> result = bookServiceImpl.getAllBooks(page, size);
            // ---
            assertNotNull(result);
            assertEquals(2, result.size());
        }

    }

}
