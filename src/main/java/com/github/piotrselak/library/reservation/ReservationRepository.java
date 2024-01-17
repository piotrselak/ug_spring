package com.github.piotrselak.library.reservation;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);
    List<Reservation> findAllByBook(Book book);
}
