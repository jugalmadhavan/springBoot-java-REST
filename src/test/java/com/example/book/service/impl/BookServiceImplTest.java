package com.example.book.service.impl;

import com.example.book.controller.objects.CreateBookInput;
import com.example.book.data.Book;
import com.example.book.repository.BookRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

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
            System.out.println("result " + result);
            assertNotNull(result);
        }


    }


}
