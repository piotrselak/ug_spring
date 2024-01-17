package com.github.piotrselak.library.stats;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class StatsService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public StatsService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Stats getStats() {
        var books = bookRepository.findAll();
        var usersByReservations = userRepository.findAll().stream()
                .sorted(Comparator.comparingInt(user -> user.getReservation().size()))
                .toList();
        var booksByVotes = books
                .stream()
                .sorted(Comparator.comparingInt(Book::getVotes))
                .toList();
        Stats stats = new Stats();
        stats.setUsersByReservations(usersByReservations);
        stats.setBooksByVotes(booksByVotes);
        return stats;
    }
}
