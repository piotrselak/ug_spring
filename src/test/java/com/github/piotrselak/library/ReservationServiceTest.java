package com.github.piotrselak.library;

import com.github.piotrselak.library.book.domain.Book;
import com.github.piotrselak.library.book.repository.BookRepository;
import com.github.piotrselak.library.reservation.Reservation;
import com.github.piotrselak.library.reservation.ReservationRepository;
import com.github.piotrselak.library.reservation.ReservationService;
import com.github.piotrselak.library.reservation.ReservationState;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testCreateReservation() {
        User mockUser = new User();
        Book mockBook = new Book();

        when(userRepository.findByName(anyString())).thenReturn(mockUser);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(mockBook));

        reservationService.createReservation(1L, "testUserName");

        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    public void testConfirmReservation() {
        Reservation mockReservation = new Reservation();

        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(mockReservation));

        reservationService.confirmReservation(1L);

        assertEquals(ReservationState.CONFIRMED, mockReservation.getState());

        verify(reservationRepository).save(mockReservation);
    }

    @Test
    public void testDeleteReservation() {
        reservationService.deleteReservation(1L);

        verify(reservationRepository).deleteById(1L);
    }
}
