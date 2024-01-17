package com.github.piotrselak.library.book;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Book>> getMostPopularBooks() {
        return new ResponseEntity<>(bookService.getMostPopularBooks(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBySearchParams(@RequestParam String title,
                                                        @RequestParam String authors,
                                                        @RequestParam String genres) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        var book = bookService.findBookById(Long.parseLong(id));
        return book
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

}
