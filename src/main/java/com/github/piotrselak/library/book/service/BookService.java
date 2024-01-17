package com.github.piotrselak.library.book.service;

import com.github.piotrselak.library.book.domain.Author;
import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.domain.Genre;
import com.github.piotrselak.library.book.repository.AuthorRepository;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.book.repository.GenreRepository;
import com.github.piotrselak.library.comment.Comment;
import com.github.piotrselak.library.comment.CommentDto;
import com.github.piotrselak.library.comment.CommentRepository;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<Book> getMostPopularBooks() {
        return bookRepository
                .findAll()
                .stream()
                .sorted((book, other) -> {
                    var rating = (float) book.getRate() / (float) book.getVotes();
                    var otherRating = (float) other.getRate() / (float) other.getVotes();
                    return Float.compare(otherRating, rating);
                })
                .limit(5)
                .toList();
    }

    public List<Book> getBooksByFilterParams(String title, List<String> authors, List<String> genres) {
        var books = bookRepository.findAll().stream();
        if (title != null && !title.isEmpty()) {
            books = books.filter(book -> book.getTitle().contains(title));
        }
        if (authors != null && !authors.isEmpty()) {
            System.out.println("Authors");
            System.out.println(authors);
            books = books
                    .filter(book -> {
                        var names = book.getAuthors().stream().map(Author::getName).toList();
                        return CollectionUtils.containsAny(names, authors);
                    });
        }
        if (genres != null && !genres.isEmpty()) {
            books = books
                    .filter(book -> {
                        var names = book.getGenres().stream().map(Genre::getName).toList();
                        return CollectionUtils.containsAny(names, genres);
                    });
        }
        return books.toList();
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void rateBook(int rating, long id) {
        var book = bookRepository.findById(id).get();
        book.setRate(book.getRate() + rating);
        book.setVotes(book.getVotes() + 1);
        bookRepository.save(book);
    }

    public List<CommentDto> findCommentsByBook(Book book) {
        return commentRepository
                .findCommentsByBook(book)
                .stream()
                .map(CommentDto::fromEntity)
                .toList();
    }

    public void addCommentToBook(Long bookId, CommentDto commentDto) {
        User user = null;
        if (!Objects.equals(commentDto.getAuthor(), "anonymousUser"))
            user = userRepository.findByName(commentDto.getAuthor());

        Book book = bookRepository.findById(bookId).get();

        Comment comment = new Comment();
        comment.setBook(book);
        comment.setText(commentDto.getText());
        comment.setAuthor(user);
        commentRepository.save(comment);
    }
}
