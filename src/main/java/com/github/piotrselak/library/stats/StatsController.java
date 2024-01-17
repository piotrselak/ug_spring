package com.github.piotrselak.library.stats;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats/booksByVotes")
    public ResponseEntity<List<Book>> getBooksByVotes() {
        var res = statsService.getBooksByVotesStats();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/stats/usersByReservations")
    public ResponseEntity<List<User>> getUsersByReservations() {
        var res = statsService.getUsersByReservationsStats();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
