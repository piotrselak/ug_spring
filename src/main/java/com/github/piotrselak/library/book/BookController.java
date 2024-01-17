package com.github.piotrselak.library.book;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.domain.RatingDto;
import com.github.piotrselak.library.book.service.BookService;
import com.github.piotrselak.library.comment.CommentDto;
import com.github.piotrselak.library.comment.CreateCommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Book>> getBySearchParams(@RequestParam(required = false) String title,
                                                        @RequestParam(required = false) String authors,
                                                        @RequestParam(required = false) String genres) {
        List<String> authorNames = new ArrayList<>();
        if (authors != null)
            authorNames = Arrays.stream(authors.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());

        List<String> genreNames = new ArrayList<>();
        ;
        if (genres != null)
            genreNames = Arrays.stream(genres.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());

        var books = bookService.getBooksByFilterParams(title, authorNames, genreNames);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        var book = bookService.findBookById(Long.parseLong(id));
        return book
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> rateBook(@RequestBody RatingDto rating,
                                         @PathVariable String id) {
        bookService.rateBook(rating.getRating(), Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/comment")
    public ResponseEntity<List<CommentDto>> getCommentsForBook(@PathVariable String id) {
        var book = bookService.findBookById(Long.parseLong(id));

        if (book.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var comments = bookService.findCommentsByBook(book.get());

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Void> addCommentForBook(@PathVariable String id,
                                                  @RequestBody CreateCommentDto commentDto) {
        var comment = new CommentDto();
        comment.setText(commentDto.getText());
        comment.setAuthor(""); // logic later

        bookService.addCommentToBook(Long.parseLong(id), comment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
