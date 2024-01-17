package com.github.piotrselak.library;

import com.github.piotrselak.library.comment.Comment;
import com.github.piotrselak.library.comment.CommentRepository;
import com.github.piotrselak.library.book.domain.Author;
import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.domain.Genre;
import com.github.piotrselak.library.book.repository.AuthorRepository;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.book.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class BookDataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public BookDataLoader(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) {
        if (!bookRepository.existsByTitle("Krótka historia czasu. Od Wielkiego Wybuchu do czarnych dziur")) {
            Book book = new Book();
            book.setTitle("Krótka historia czasu. Od Wielkiego Wybuchu do czarnych dziur");
            book.setImageUrl("https://s.lubimyczytac.pl/upload/books/4808000/4808886/655586-352x500.jpg");
            book.setDescription("„Krótka historia czasu” to już klasyczna pozycja literatury popularnonaukowej.");
            book.setReleaseDate(LocalDate.of(2015, 11, 2));
            book.setRate(5);
            book.setVotes(1);
            book.setPublishingHouse("Zysk i S-ka");

            Genre genre = new Genre();
            genre.setName("astronomy");
            genreRepository.save(genre);

            Author author = new Author();
            author.setName("Stephen Hawking");
            authorRepository.save(author);

            book.setGenres(Set.of(genre));
            book.setAuthors(Set.of(author));

            bookRepository.save(book);

            Comment comment = new Comment();
            comment.setText("Great book!");
            comment.setBook(book);
            commentRepository.save(comment);
        }

        // next
        Book newBook = new Book();
        newBook.setTitle("The Chronicles of Narnia");
        newBook.setImageUrl("https://s.lubimyczytac.pl/upload/books/91000/91987/352x500.jpg");
        newBook.setDescription("A series of fantasy novels by C.S. Lewis.");
        newBook.setReleaseDate(LocalDate.of(1950, 10, 16));
        newBook.setRate(5000);
        newBook.setVotes(1000);
        newBook.setPublishingHouse("HarperCollins");

        Genre fantasyGenre = new Genre();
        fantasyGenre.setName("fantasy");
        fantasyGenre = genreRepository.save(fantasyGenre);

        Author csLewis = new Author();
        csLewis.setName("C.S. Lewis");
        csLewis = authorRepository.save(csLewis);

        newBook.setGenres(Set.of(fantasyGenre));
        newBook.setAuthors(Set.of(csLewis));

        bookRepository.save(newBook);
        // end next

        //next
        Book anotherBook = new Book();
        anotherBook.setTitle("The Lord of the Rings");
        anotherBook.setImageUrl("https://s.lubimyczytac.pl/upload/books/209000/209143/628379-352x500.jpg");
        anotherBook.setDescription("A high-fantasy epic written by J.R.R. Tolkien.");
        anotherBook.setReleaseDate(LocalDate.of(1954, 7, 29));
        anotherBook.setRate(6000);
        anotherBook.setVotes(1500);
        anotherBook.setPublishingHouse("George Allen & Unwin");

        Author tolkien = new Author();
        tolkien.setName("J.R.R. Tolkien");
        tolkien = authorRepository.save(tolkien);

        anotherBook.setGenres(Set.of(fantasyGenre));
        anotherBook.setAuthors(Set.of(tolkien));

        bookRepository.save(anotherBook);
        //end next

        //next
        Book gameOfThrones = new Book();
        gameOfThrones.setTitle("A Game of Thrones");
        gameOfThrones.setImageUrl("https://s.lubimyczytac.pl/upload/books/32000/32296/352x500.jpg");
        gameOfThrones.setDescription("First book in the 'A Song of Ice and Fire' series by George R.R. Martin.");
        gameOfThrones.setReleaseDate(LocalDate.of(1996, 8, 6));
        gameOfThrones.setRate(6000);
        gameOfThrones.setVotes(1500);
        gameOfThrones.setPublishingHouse("Bantam Spectra");

        Author martin = new Author();
        martin.setName("George R.R. Martin");
        martin = authorRepository.save(martin);

        gameOfThrones.setGenres(Set.of(fantasyGenre));
        gameOfThrones.setAuthors(Set.of(martin));

        bookRepository.save(gameOfThrones);
        //end next

        //next
        Book murderOnTheOrientExpress = new Book();
        murderOnTheOrientExpress.setTitle("Murder on the Orient Express");
        murderOnTheOrientExpress.setImageUrl("https://s.lubimyczytac.pl/upload/books/4936000/4936102/984743-352x500.jpg");
        murderOnTheOrientExpress.setDescription("A famous detective Hercule Poirot solves a murder mystery on a luxurious train.");
        murderOnTheOrientExpress.setReleaseDate(LocalDate.of(1934, 1, 1));
        murderOnTheOrientExpress.setRate(4800);
        murderOnTheOrientExpress.setVotes(1200);
        murderOnTheOrientExpress.setPublishingHouse("Collins Crime Club");

        Genre mysteryGenre = new Genre();
        mysteryGenre.setName("mystery");
        mysteryGenre = genreRepository.save(mysteryGenre);

        Author christie = new Author();
        christie.setName("Agatha Christie");
        christie = authorRepository.save(christie);

        murderOnTheOrientExpress.setGenres(Set.of(mysteryGenre));
        murderOnTheOrientExpress.setAuthors(Set.of(christie));

        bookRepository.save(murderOnTheOrientExpress);
        //end next

        //next
        Book andThenThereWereNone = new Book();
        andThenThereWereNone.setTitle("And Then There Were None");
        andThenThereWereNone.setImageUrl("https://s.lubimyczytac.pl/upload/books/4971000/4971805/906906-352x500.jpg");
        andThenThereWereNone.setDescription("Ten strangers are invited to an island by an unknown host, and a series of murders occur.");
        andThenThereWereNone.setReleaseDate(LocalDate.of(1939, 1, 1));
        andThenThereWereNone.setRate(6000);
        andThenThereWereNone.setVotes(1500);
        andThenThereWereNone.setPublishingHouse("Collins Crime Club");
        andThenThereWereNone.setGenres(Set.of(mysteryGenre));
        andThenThereWereNone.setAuthors(Set.of(christie));

        bookRepository.save(andThenThereWereNone);

        //end next
    }
}