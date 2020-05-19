package com.example.book.repository;

import com.example.book.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByISBN(Long isbn);

    Long deleteByISBN(Long isbn);

}
