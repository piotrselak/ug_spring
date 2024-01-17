package com.github.piotrselak.library.admin;

import com.github.piotrselak.library.book.domain.Author;
import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.domain.CreateBookDto;
import com.github.piotrselak.library.book.domain.Genre;
import com.github.piotrselak.library.book.repository.AuthorRepository;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.book.repository.GenreRepository;
import com.github.piotrselak.library.comment.Comment;
import com.github.piotrselak.library.comment.CommentRepository;
import com.github.piotrselak.library.reservation.ReservationRepository;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final ReservationRepository reservationRepository;

    public AdminService(UserRepository userRepository, CommentRepository commentRepository, BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.reservationRepository = reservationRepository;
    }

    List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    boolean removeUser(long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) return false;
        var reservations = reservationRepository.findAllByUser(user.get());
        reservationRepository.deleteAll(reservations);
        userRepository.deleteById(id);
        return true;
    }

    boolean changeUserName(String name, long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) return false;

        User u = user.get();
        u.setName(name);
        userRepository.save(u);
        return true;
    }

    List<Comment> fetchAllComments() {
        return commentRepository.findAll();
    }

    boolean removeComment(long id) {
        var user = commentRepository.findById(id);
        if (user.isEmpty()) return false;

        commentRepository.deleteById(id);
        return true;
    }

    List<Book> fetchAllBooks() {
        return bookRepository.findAll();
    }

    boolean removeBook(long id) {
        var book = bookRepository.findById(id);
        if (book.isEmpty()) return false;

        var comments = commentRepository.findCommentsByBook(book.get());
        commentRepository.deleteAll(comments);
        var reservations = reservationRepository.findAllByBook(book.get());
        reservationRepository.deleteAll(reservations);
        bookRepository.deleteById(id);
        return true;
    }

    void createBook(CreateBookDto bookDto) {
        List<Genre> genres = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        for (var genreName : bookDto.getGenres()) {
            var genre = genreRepository.findByName(genreName);
            if (genre.isEmpty()) {
                Genre newGenre = new Genre();
                newGenre.setName(genreName);
                newGenre = genreRepository.save(newGenre);
                genres.add(newGenre);
            } else {
                genres.add(genre.get());
            }
        }

        for (var authorName : bookDto.getAuthors()) {
            var author = authorRepository.findByName(authorName);
            if (author.isEmpty()) {
                Author newAuthor = new Author();
                newAuthor.setName(authorName);
                newAuthor = authorRepository.save(newAuthor);
                authors.add(newAuthor);
            } else {
                authors.add(author.get());
            }
        }

        Book book = new Book();
        book.setAuthors(new HashSet<>(authors));
        book.setGenres(new HashSet<>(genres));
        book.setRate(bookDto.getRate());
        book.setVotes(bookDto.getVotes());
        book.setDescription(bookDto.getDescription());
        book.setTitle(bookDto.getTitle());
        book.setPublishingHouse(bookDto.getPublishingHouse());
        book.setImageUrl(bookDto.getImageUrl());
        book.setReleaseDate(bookDto.getReleaseDate());

        bookRepository.save(book);
    }

    Book fetchBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    boolean updateBook(Long id, CreateBookDto bookDto) {
        var bookO = bookRepository.findById(id);
        if (bookO.isEmpty()) return false;

        var book = bookO.get();

        List<Genre> genres = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        for (var genreName : bookDto.getGenres()) {
            var genre = genreRepository.findByName(genreName);
            if (genre.isEmpty()) {
                Genre newGenre = new Genre();
                newGenre.setName(genreName);
                newGenre = genreRepository.save(newGenre);
                genres.add(newGenre);
            } else {
                genres.add(genre.get());
            }
        }

        for (var authorName : bookDto.getAuthors()) {
            var author = authorRepository.findByName(authorName);
            if (author.isEmpty()) {
                Author newAuthor = new Author();
                newAuthor.setName(authorName);
                newAuthor = authorRepository.save(newAuthor);
                authors.add(newAuthor);
            } else {
                authors.add(author.get());
            }
        }
        book.setAuthors(new HashSet<>(authors));
        book.setGenres(new HashSet<>(genres));
        book.setRate(bookDto.getRate());
        book.setVotes(bookDto.getVotes());
        book.setDescription(bookDto.getDescription());
        book.setTitle(bookDto.getTitle());
        book.setPublishingHouse(bookDto.getPublishingHouse());
        book.setImageUrl(bookDto.getImageUrl());
        book.setReleaseDate(bookDto.getReleaseDate());

        bookRepository.save(book);
        return true;
    }
}
