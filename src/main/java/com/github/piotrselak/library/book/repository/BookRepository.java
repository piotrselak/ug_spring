package com.github.piotrselak.library.book.repository;

import com.github.piotrselak.library.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
}
