package com.github.piotrselak.library.stats;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.user.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class Stats {
    public List<User> usersByReservations;
    public List<Book> booksByVotes;
}
