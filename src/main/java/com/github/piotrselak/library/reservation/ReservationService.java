package com.github.piotrselak.library.reservation;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<Reservation> findAllByUser(String name) {
        var user = userRepository.findByName(name);
        return reservationRepository.findAllByUser(user);
    }

    public void createReservation(Long bookId, String userName) {
        var user = userRepository.findByName(userName);
        Book book = bookRepository.findById(bookId).get();
        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setUser(user);
        reservation.setState(ReservationState.NOT_CONFIRMED);
        reservationRepository.save(reservation);
    }

    public void confirmReservation(Long id) {
        var reservation = reservationRepository.findById(id).get();
        reservation.setState(ReservationState.CONFIRMED);
        reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}