package com.github.piotrselak.library.reservation;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Enumerated(EnumType.ORDINAL)
    ReservationState state;
}
