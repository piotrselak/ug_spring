package com.github.piotrselak.library.stats;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StatsService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public StatsService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<User> getUsersByReservationsStats() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparingInt(user -> user.getReservation().size()))
                .toList();
    }

    public List<Book> getBooksByVotesStats() {
        return bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Book::getVotes))
                .toList();
    }
}
